using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Mvc;


namespace SuperVigilanciaPresentacion
{
    public class UsuarioController : Controller
    {
        string Baseurl = "http://10.192.12.26/";
        // GET: Default|
        public ActionResult Index()
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

                    ModelState.AddModelError(string.Empty, "Server error. Please contact administrator.");
                }
            }
            return View(usuario);

            
        }

        // GET: Default/Details/5
        public ActionResult Details(int id)
        {
            Usuario usuario = null;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Baseurl);
                //HTTP GET
                var responseTask = client.GetAsync("Usuario/" + id);
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readTask = result.Content.ReadAsAsync<Usuario>();
                    readTask.Wait();

                    usuario = readTask.Result;
                }
                else //web api sent error response
                {
                    //log response status here..

                    usuario = null;

                    ModelState.AddModelError(string.Empty, "Server error. Please contact administrator.");
                }
            }
            return View(usuario);
        }

        // GET: Default/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Default/Create
        [HttpPost]
        public ActionResult Create(FormCollection collection)
        {
            try
            {
                // TODO: Add insert logic here
                Usuario newUser = new Usuario(0, collection.Get("Tipo"), collection.Get("DocIdentidad"), collection.Get("Nombre"), collection.Get("Contrasena"), collection.Get("Email"));

                using (var client = new HttpClient())
                {
                    client.BaseAddress = new Uri(Baseurl);
                    //HTTP GET
                    var responseTask = client.PostAsJsonAsync("Usuario", newUser);
                    responseTask.Wait();

                    var result = responseTask.Result;
                    return RedirectToAction("Index");
                }
            }
            catch
            {
                return Index();
            }
        }

        // GET: Default/Edit/5
        public ActionResult Edit(int id)
        {
            Usuario usuario = null;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Baseurl);
                //HTTP GET
                var responseTask = client.GetAsync("Usuario/" + id);
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readTask = result.Content.ReadAsAsync<Usuario>();
                    readTask.Wait();

                    usuario = readTask.Result;
                }
                else //web api sent error response
                {
                    //log response status here..

                    usuario = null;

                    ModelState.AddModelError(string.Empty, "Server error. Please contact administrator.");
                }
            }
            return View(usuario);
        }

        // POST: Default/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add insert logic here
                Usuario newUser = new Usuario(int.Parse(collection.Get("Id")), collection.Get("Tipo"), collection.Get("DocIdentidad"), collection.Get("Nombre"), collection.Get("Contrasena"), collection.Get("Email"));

                using (var client = new HttpClient())
                {
                    client.BaseAddress = new Uri(Baseurl);
                    //HTTP GET
                    var responseTask = client.PutAsJsonAsync("Usuario/" + id, newUser);
                    responseTask.Wait();

                    var result = responseTask.Result;
                    return RedirectToAction("Index");
                }
            }
            catch
            {
                return View(id);
            }
        }

        // GET: Default/Delete/5
        public ActionResult Delete(int id)
        {
            Usuario usuario = null;
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Baseurl);
                //HTTP GET
                var responseTask = client.GetAsync("Usuario/" + id);
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readTask = result.Content.ReadAsAsync<Usuario>();
                    readTask.Wait();

                    usuario = readTask.Result;
                }
                else //web api sent error response
                {
                    //log response status here..

                    usuario = null;

                    ModelState.AddModelError(string.Empty, "Server error. Please contact administrator.");
                }
            }
            return View(usuario);
        }

        // POST: Default/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add delete logic here

                using (var client = new HttpClient())
                {
                    client.BaseAddress = new Uri(Baseurl);
                    //HTTP GET
                    var responseTask = client.DeleteAsync("Usuario/" + id);
                    responseTask.Wait();

                    var result = responseTask.Result;
                }
                return RedirectToAction("Index");
            }
            catch (Exception)
            {
                return View();
            }
        }
    }
}
