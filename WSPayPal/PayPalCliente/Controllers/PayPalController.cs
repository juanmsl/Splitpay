using PayPalCliente.ProxyPayPal;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace PayPalCliente
{
    public class PayPalController : Controller
    {
        // GET: PayPal
        public ActionResult Index()
        {
            return View();
        }
        [HttpPost]
        public ActionResult Index(FormCollection usuario)
        {
            Usuario user = new Usuario();
            user.DocIdentidad = usuario.Get("DocIdentidad");
            user.Tipo = usuario.Get("Tipo");
            user.Contrasena = usuario.Get("Contrasena");
            System.Web.HttpContext.Current.Session["User"] = user;
            int monto = int.Parse(usuario.Get("Nombre"));
            System.Web.HttpContext.Current.Session["Monto"] = monto;
            return RedirectToAction("Pagar");
        }

        public ActionResult Pagar() {

            ProxyPayPal.WSUsuarioClient proxy = new ProxyPayPal.WSUsuarioClient();
            proxy.PagarAsync(System.Web.HttpContext.Current.Session["User"] as Usuario, (int)System.Web.HttpContext.Current.Session["Monto"]);

            return View();
        }
    }
}