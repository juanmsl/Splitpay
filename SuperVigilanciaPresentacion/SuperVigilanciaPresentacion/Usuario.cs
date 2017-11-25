using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SuperVigilanciaPresentacion
{
    public partial class Usuario
    {
        public Usuario()
        {
            this.Transaccion = new HashSet<Transaccion>();
        }

        public Usuario(int id, string tipo, string docIdentidad, string nombre, string contrasena, string email)
        {
            Id = id;
            Tipo = tipo;
            DocIdentidad = docIdentidad;
            Nombre = nombre;
            Contrasena = contrasena;
            Email = email;
            Transaccion = new HashSet<Transaccion>();
        }

        public int Id { get; set; }
        public string Tipo { get; set; }
        public string DocIdentidad { get; set; }
        public string Nombre { get; set; }
        public string Contrasena { get; set; }
        public string Email { get; set; }
        
        public virtual ICollection<Transaccion> Transaccion { get; set; }
    }
}