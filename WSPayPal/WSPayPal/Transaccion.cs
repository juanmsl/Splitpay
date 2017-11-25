using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WSPayPal
{
    public class Transaccion
    {
        public Transaccion(int id, string concepto, string descripcion, int? valor, DateTime? fecha, int? usuario_ID)
        {
            Id = id;
            Concepto = concepto;
            Descripcion = descripcion;
            Valor = valor;
            Fecha = fecha;
            Usuario_ID = usuario_ID;
        }

        public int Id { get; set; }
        public string Concepto { get; set; }
        public string Descripcion { get; set; }
        public Nullable<int> Valor { get; set; }
        public Nullable<System.DateTime> Fecha { get; set; }
        public Nullable<int> Usuario_ID { get; set; }
    }
}