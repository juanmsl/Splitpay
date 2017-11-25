using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace JavaSOAP
{
    public class TransaccionController : Controller
    {
        // GET: Transaccion
        public ActionResult Index()
        {
            return View(new SearchDate());
        }

        [HttpPost]
        public ActionResult Index(FormCollection collection)
        {
            ProxyTransaccion.WSTransactionClient proxy = new ProxyTransaccion.WSTransactionClient();
            IEnumerable<ProxyTransaccion.pago> pagos = Enumerable.Empty<ProxyTransaccion.pago>();
            if (collection.Get("Documento") != null && collection.Get("Documento") != "")
            {
                ProxyTransaccion.pago[] b = proxy.findByDocument(collection.Get("Documento"));
                if (b != null)
                    pagos = b.ToList();
            }
            else if (collection.Get("Fecha") != null && collection.Get("Fecha") != "")
            {
                DateTime fecha = DateTime.Parse(collection.Get("Fecha"));
                ProxyTransaccion.pago[] a = proxy.findByDate(fecha);
                if (a != null)
                    pagos = a.ToList();
            }
            System.Web.HttpContext.Current.Session["data"] = pagos;
            return RedirectToAction("DisplayPagos");
        }

        public ActionResult DisplayPagos(IEnumerable<ProxyTransaccion.pago> pagos)
        {
            return View(System.Web.HttpContext.Current.Session["data"]);
        }
    }
}