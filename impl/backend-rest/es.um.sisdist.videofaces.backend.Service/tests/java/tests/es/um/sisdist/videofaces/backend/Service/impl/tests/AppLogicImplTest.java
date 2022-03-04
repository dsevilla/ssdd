/**
 *
 */
package es.um.sisdist.videofaces.backend.Service.impl.tests;

/**
 * @author dsevilla
 *
 */
class AppLogicImplTest
{
        static AppLogicImpl impl;

        @BeforeAll
        static void setup()
        {
                impl = AppLogicImpl.getInstance();
        }

        @Test
        void testDefaultUser()
        {
                Optional<User> u = impl.getUserByEmail("dsevilla@um.es");
                assertEquals(u.get().getEmail(), "dsevilla@um.es");
        }
}
