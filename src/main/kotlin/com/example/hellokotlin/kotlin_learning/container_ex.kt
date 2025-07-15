import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor
import kotlin.text.set

@Target(AnnotationTarget.CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
annotation class Inject

typealias Provider<T> = Container.() -> T

class Container {
    private val singletons = mutableMapOf<KClass<*>, Any>()
    private val providers = mutableMapOf<KClass<*>, Provider<*>>()

    fun <T : Any> singleton(clazz: KClass<T>, provider: Provider<T>) {
        providers[clazz] = {
            val self = this
            @Suppress("UNCHECKED_CAST")
            singletons.getOrPut(clazz) { self.provider() } as T
        }
    }

    fun <T : Any> factory(clazz: KClass<T>, provider: Provider<T>) {
        providers[clazz] = provider
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> get(clazz: KClass<T>): T {
        val provider = providers[clazz] ?: autoResolveProvider(clazz)
        return provider?.invoke(this) as? T
            ?: throw IllegalStateException("No provider found for $clazz")
    }

    private fun <T : Any> autoResolveProvider(clazz: KClass<T>): Provider<T>? {
        val constructor = clazz.constructors.find {
            it.annotations.any { it is Inject }
        } ?: clazz.primaryConstructor

        if (constructor != null) {
            val provider: Provider<T> = {
                val params = constructor.parameters.map { param ->
                    val paramClass = param.type.classifier as? KClass<*>
                        ?: throw IllegalArgumentException("Cannot resolve parameter type")
                    get(paramClass as KClass<Any>)
                }
                constructor.call(*params.toTypedArray())
            }
            providers[clazz] = provider
            return provider
        }
        return null
    }

    inline fun <reified T : Any> get(): T = get(T::class)
}

class ContainerBuilder {
    private val actions = mutableListOf<Container.() -> Unit>()

    fun <T : Any> singleton(clazz: KClass<T>, provider: Container.() -> T) {
        actions += { singleton(clazz, provider) }
    }
    inline fun <reified T : Any> singleton(noinline provider: Container.() -> T) =
        singleton(T::class, provider)

    fun <T : Any> factory(clazz: KClass<T>, provider: Container.() -> T) {
        actions += { factory(clazz, provider) }
    }
    inline fun <reified T : Any> factory(noinline provider: Container.() -> T) =
        factory(T::class, provider)

    fun build(): Container = Container().apply { actions.forEach { it(this) } }
}

fun container(init: ContainerBuilder.() -> Unit): Container =
    ContainerBuilder().apply(init).build()

// 예시 클래스
interface Database
class MySQLDatabase : Database

interface Service
class UserService(val db: Database) : Service

fun main() {
    val container = container {
        singleton<Database> { MySQLDatabase() }
        factory<Service> { UserService(get()) }
    }

    val service: Service = container.get()
    println(service is UserService) // true
    println((service as UserService).db is MySQLDatabase) // true
}