package dao;

/**
 * Fábrica de DAOs — implementa el patrón Factory.
 * Centraliza la creación de objetos DAO para que el resto
 * del código no dependa de clases concretas (solo de interfaces).
 *
 * También actúa como Singleton del DAO: reutiliza la misma
 * instancia de UsuarioDao en toda la aplicación.
 */
public class DaoFactory {

    // Instancia única del DAO (se crea solo la primera vez)
    private static IUsuarioDao usuarioDao;

    /**
     * Retorna la instancia única de IUsuarioDao.
     * synchronized evita que dos hilos creen dos instancias al mismo tiempo.
     * @return implementación activa de IUsuarioDao (actualmente MySQL)
     */
    public static synchronized IUsuarioDao getUsuarioDao() {
        if (usuarioDao == null) {
            // Aquí se decide qué implementación usar.
            // Si mañana cambias a PostgreSQL, solo cambias esta línea.
            usuarioDao = new UsuarioDao();
        }
        return usuarioDao;
    }
}