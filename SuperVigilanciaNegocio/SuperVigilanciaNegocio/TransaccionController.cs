using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;

namespace SuperVigilanciaNegocio
{
    public class TransaccionController : ApiController
    {
        private SuperVigilanciaDBEntities db = new SuperVigilanciaDBEntities();

        // GET: api/Transaccion
        public IQueryable<Transaccion> GetTransaccion()
        {
            return db.Transaccion;

        }

        // GET: api/Transaccion/5
        //[ResponseType(typeof(void))]
        public IHttpActionResult GetTransaccion(int id)
        {
            Transaccion transaccion = db.Transaccion.Find(id);
            if (transaccion == null)
            {
                return NotFound();
            }

            return Ok(transaccion);
        }
        // GET: api/Transaccion/5
        //[ResponseType(typeof(void))]
        [Route("Transaccion/{id}/Transacciones")]
        public IHttpActionResult GetTransaccionByUser(int id)
        {
            List<Transaccion> transaccion = db.Transaccion.Where(x => x.Usuario_ID == id).ToList();
            return Ok(transaccion);
        }

        // PUT: api/Transaccion/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutTransaccion(int id, Transaccion transaccion)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != transaccion.Id)
            {
                return BadRequest();
            }

            db.Entry(transaccion).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!TransaccionExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/Transaccion
        [ResponseType(typeof(Transaccion))]
        public IHttpActionResult PostTransaccion(Transaccion transaccion)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Transaccion.Add(transaccion);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (TransaccionExists(transaccion.Id))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = transaccion.Id }, transaccion);
        }

        // DELETE: api/Transaccion/5
        [ResponseType(typeof(Transaccion))]
        public IHttpActionResult DeleteTransaccion(int id)
        {
            Transaccion transaccion = db.Transaccion.Find(id);
            if (transaccion == null)
            {
                return NotFound();
            }

            db.Transaccion.Remove(transaccion);
            db.SaveChanges();

            return Ok(transaccion);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool TransaccionExists(int id)
        {
            return db.Transaccion.Count(e => e.Id == id) > 0;
        }

        [Route("Transaccion/ByFecha/{id}/{fecha}")]
        public IHttpActionResult PutFindByDate(int id, string fechaStr)
        {
            DateTime fecha = DateTime.Parse(fechaStr);
            List<Transaccion> transaccion = db.Transaccion.Where(x => ((DateTime)x.Fecha - fecha).TotalHours < 24).ToList();
            return Ok(transaccion);
        }
    }
}