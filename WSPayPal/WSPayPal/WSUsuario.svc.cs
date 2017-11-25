using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace WSPayPal
{
    // NOTA: puede usar el comando "Rename" del menú "Refactorizar" para cambiar el nombre de clase "WSUsuario" en el código, en svc y en el archivo de configuración a la vez.
    // NOTA: para iniciar el Cliente de prueba WCF para probar este servicio, seleccione WSUsuario.svc o WSUsuario.svc.cs en el Explorador de soluciones e inicie la depuración.
    public class WSUsuario : IWSUsuario
    {
        string Baseurl = "http://10.192.12.26";

        public bool Pagar(Usuario user, int monto)
        {
            IEnumerable<Usuario> usuario = null;

            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Baseurl);
                //HTTP GET
                var responseTask = client.GetAsync("Usuario");
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readTask = result.Content.ReadAsAsync<IList<Usuario>>();
                    readTask.Wait();

                    usuario = readTask.Result;
                }
                else //web api sent error response
                {
                    //log response status here..

                    usuario = Enumerable.Empty<Usuario>();
                }
                foreach (Usuario x in usuario)
                {
                    if (x.Tipo == user.Tipo && x.DocIdentidad == user.DocIdentidad && x.Contrasena == user.Contrasena)
                    {
                        Transaccion tr = new Transaccion(0, "PayPal", "Pagado desde PayPal", monto, new DateTime(), x.Id);
                        responseTask = client.PostAsJsonAsync("Transaccion", tr);
                        responseTask.Wait();
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
