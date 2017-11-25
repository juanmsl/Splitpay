using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WSPayPal
{
    public class Usuario
    {
        public int Id { get; set; }
        public string Tipo { get; set; }
        public string DocIdentidad { get; set; }
        public string Nombre { get; set; }
        public string Contrasena { get; set; }
        public string Email { get; set; }
    }
}