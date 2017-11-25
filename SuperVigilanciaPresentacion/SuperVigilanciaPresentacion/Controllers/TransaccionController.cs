using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Mvc;

namespace SuperVigilanciaPresentacion
{
    public class TransaccionController : Controller
    {
        string Baseurl = "http://10.192.12.26/";
        //string Baseurl = "http://svnegocio.splitpay.com/";

        // GET: Transaccion
        public ActionResult Index()
        {
            IEnumerable<Transaccion> transaccion = null;

            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Baseurl);
                //HTTP GET
                var responseTask = client.GetAsync("Transaccion");
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readTask = result.Content.ReadAsAsync<IList<Transaccion>>();
                    readTask.Wait();

                    transaccion = readTask.Result;
                }
                else //web api sent error response
                {
                    //log response status here..

                    transaccion = Enumerable.Empty<Transaccion>();

                    ModelState.AddModelError(string.Empty, "Server error. Please contact administrator.");
                }
            }
            return View(transaccion);
        }
        public ActionResult ByUser(int id)
        {
            IEnumerable<Transaccion> transaccion = null;

            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Baseurl);
                //HTTP GET
                var responseTask = client.GetAsync("Usuario/" + id + "/Transacciones");
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readTask = result.Content.ReadAsAsync<IList<Transaccion>>();
                    readTask.Wait();

                    transaccion = readTask.Result;
                }
                else //web api sent error response
                {
                    //log response status here..

                    transaccion = Enumerable.Empty<Transaccion>();

                    ModelState.AddModelError(string.Empty, "Server error. Please contact administrator.");
                }
            }
            return View(transaccion);
        }

        // GET: Transaccion/Details/5
        public ActionResult Details(int id)
        {
            Transaccion transaccion = null;

            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Baseurl);
                //HTTP GET
                var responseTask = client.GetAsync("Transaccion/" + id);
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readTask = result.Content.ReadAsAsync<Transaccion>();
                    readTask.Wait();

                    transaccion = readTask.Result;
                }
                else //web api sent error response
                {
                    //log response status here..

                    transaccion = null;

                    ModelState.AddModelError(string.Empty, "Server error. Please contact administrator.");
                }
            }
            return View(transaccion);
        }

        // GET: Transaccion/Create
        public ActionResult DateByUser(int id)
        {
            return View(new Transaccion(0, null, null, null, null, id, null));
        }
        [HttpPost]
        // GET: Transaccion/Create
        public ActionResult DateByUser(int id, FormCollection collection)
        {
            IEnumerable<Transaccion> transaccion = null;

            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Baseurl);
                //HTTP GET
                var responseTask = client.GetAsync("Usuario/" + id + "/Transacciones");
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readTask = result.Content.ReadAsAsync<IList<Transaccion>>();
                    readTask.Wait();

                    transaccion = readTask.Result;
                }
                else //web api sent error response
                {
                    //log response status here..

                    transaccion = Enumerable.Empty<Transaccion>();

                    ModelState.AddModelError(string.Empty, "Server error. Please contact administrator.");
                }
            }
            DateTime parsed = DateTime.Parse(collection.Get("Fecha"));
            System.Web.HttpContext.Current.Session["filteredTable"] = transaccion.Where(o => (parsed - ((DateTime)o.Fecha)).TotalHours < 24);
            return RedirectToAction("DisplayTable");
        }

        public ActionResult DisplayTable()
        {
            return View(System.Web.HttpContext.Current.Session["filteredTable"] as IEnumerable<Transaccion>);
        }

        // GET: Transaccion/Create
        public ActionResult Create()
        {
            return View();
        }

        public ActionResult CreateByUser(int id)
        {
            return View(new Transaccion(0, null, null, null, null, id, null));
        }

        [HttpPost]
        public ActionResult CreateByUser(FormCollection collection)
        {
            try
            {
                // TODO: Add insert logic here
                Transaccion newTransaccion = new Transaccion(0, collection.Get("Concepto"), collection.Get("Descripcion"), int.Parse(collection.Get("Valor")), DateTime.Parse(collection.Get("Fecha")), int.Parse(collection.Get("Usuario_ID")), null);

                using (var client = new HttpClient())
                {
                    client.BaseAddress = new Uri(Baseurl);
                    //HTTP GET
                    var responseTask = client.PostAsJsonAsync("Transaccion", newTransaccion);
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

        // POST: Transaccion/Create
        [HttpPost]
        public ActionResult Create(FormCollection collection)
        {
            try
            {
                // TODO: Add insert logic here
                Transaccion newTransaccion = new Transaccion(0, collection.Get("Concepto"), collection.Get("Descripcion"), int.Parse(collection.Get("Valor")), DateTime.Parse(collection.Get("Fecha")), int.Parse(collection.Get("Usuario_ID")), null);

                using (var client = new HttpClient())
                {
                    client.BaseAddress = new Uri(Baseurl);
                    //HTTP GET
                    var responseTask = client.PostAsJsonAsync("Transaccion", newTransaccion);
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

        // GET: Transaccion/Edit/5
        public ActionResult Edit(int id)
        {
            Transaccion transaccion = null;

            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Baseurl);
                //HTTP GET
                var responseTask = client.GetAsync("Transaccion/" + id);
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readTask = result.Content.ReadAsAsync<Transaccion>();
                    readTask.Wait();

                    transaccion = readTask.Result;
                }
                else //web api sent error response
                {
                    //log response status here..

                    transaccion = null;

                    ModelState.AddModelError(string.Empty, "Server error. Please contact administrator.");
                }
            }
            return View(transaccion);
        }

        // POST: Transaccion/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add insert logic here
                Transaccion newTransaccion = new Transaccion(int.Parse(collection.Get("Id")), collection.Get("Concepto"), collection.Get("Descripcion"), int.Parse(collection.Get("Valor")), DateTime.Parse(collection.Get("Fecha")), int.Parse(collection.Get("Usuario_ID")), null);

                using (var client = new HttpClient())
                {
                    client.BaseAddress = new Uri(Baseurl);
                    //HTTP GET
                    var responseTask = client.PutAsJsonAsync("Transaccion/" + id, newTransaccion);
                    responseTask.Wait();

                    var result = responseTask.Result;
                    return RedirectToAction("Index");
                }
            }
            catch (Exception e)
            {
                return Index();
            }
        }

        // GET: Transaccion/Delete/5
        public ActionResult Delete(int id)
        {
            Transaccion transaccion = null;

            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(Baseurl);
                //HTTP GET
                var responseTask = client.GetAsync("Transaccion/" + id);
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {
                    var readTask = result.Content.ReadAsAsync<Transaccion>();
                    readTask.Wait();

                    transaccion = readTask.Result;
                }
                else //web api sent error response
                {
                    //log response status here..

                    transaccion = null;

                    ModelState.AddModelError(string.Empty, "Server error. Please contact administrator.");
                }
            }
            return View(transaccion);
        }

        // POST: Transaccion/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add insert logic here
                using (var client = new HttpClient())
                {
                    client.BaseAddress = new Uri(Baseurl);
                    //HTTP GET
                    var responseTask = client.DeleteAsync("Transaccion/" + id);
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
    }
}
