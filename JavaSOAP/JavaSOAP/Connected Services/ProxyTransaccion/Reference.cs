﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Este código fue generado por una herramienta.
//     Versión de runtime:4.0.30319.42000
//
//     Los cambios en este archivo podrían causar un comportamiento incorrecto y se perderán si
//     se vuelve a generar el código.
// </auto-generated>
//------------------------------------------------------------------------------

namespace JavaSOAP.ProxyTransaccion {
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="http://services/", ConfigurationName="ProxyTransaccion.WSTransaction")]
    public interface WSTransaction {
        
        // CODEGEN: El parámetro 'return' requiere información adicional de esquema que no se puede capturar con el modo de parámetros. El atributo específico es 'System.Xml.Serialization.XmlElementAttribute'.
        [System.ServiceModel.OperationContractAttribute(Action="http://services/WSTransaction/findByDateRequest", ReplyAction="http://services/WSTransaction/findByDateResponse")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        [return: System.ServiceModel.MessageParameterAttribute(Name="return")]
        JavaSOAP.ProxyTransaccion.findByDateResponse findByDate(JavaSOAP.ProxyTransaccion.findByDateRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://services/WSTransaction/findByDateRequest", ReplyAction="http://services/WSTransaction/findByDateResponse")]
        System.Threading.Tasks.Task<JavaSOAP.ProxyTransaccion.findByDateResponse> findByDateAsync(JavaSOAP.ProxyTransaccion.findByDateRequest request);
        
        // CODEGEN: El parámetro 'return' requiere información adicional de esquema que no se puede capturar con el modo de parámetros. El atributo específico es 'System.Xml.Serialization.XmlElementAttribute'.
        [System.ServiceModel.OperationContractAttribute(Action="http://services/WSTransaction/findByDocumentRequest", ReplyAction="http://services/WSTransaction/findByDocumentResponse")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        [return: System.ServiceModel.MessageParameterAttribute(Name="return")]
        JavaSOAP.ProxyTransaccion.findByDocumentResponse findByDocument(JavaSOAP.ProxyTransaccion.findByDocumentRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://services/WSTransaction/findByDocumentRequest", ReplyAction="http://services/WSTransaction/findByDocumentResponse")]
        System.Threading.Tasks.Task<JavaSOAP.ProxyTransaccion.findByDocumentResponse> findByDocumentAsync(JavaSOAP.ProxyTransaccion.findByDocumentRequest request);
    }
    
    /// <comentarios/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.6.1064.2")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://services/")]
    public partial class pago : object, System.ComponentModel.INotifyPropertyChanged {
        
        private string conceptoField;
        
        private string descripcionField;
        
        private deuda deudaIdField;
        
        private System.DateTime fechaField;
        
        private bool fechaFieldSpecified;
        
        private decimal idField;
        
        private bool idFieldSpecified;
        
        private string montoField;
        
        private usuario usuarioIdField;
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        public string concepto {
            get {
                return this.conceptoField;
            }
            set {
                this.conceptoField = value;
                this.RaisePropertyChanged("concepto");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        public string descripcion {
            get {
                return this.descripcionField;
            }
            set {
                this.descripcionField = value;
                this.RaisePropertyChanged("descripcion");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=2)]
        public deuda deudaId {
            get {
                return this.deudaIdField;
            }
            set {
                this.deudaIdField = value;
                this.RaisePropertyChanged("deudaId");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=3)]
        public System.DateTime fecha {
            get {
                return this.fechaField;
            }
            set {
                this.fechaField = value;
                this.RaisePropertyChanged("fecha");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool fechaSpecified {
            get {
                return this.fechaFieldSpecified;
            }
            set {
                this.fechaFieldSpecified = value;
                this.RaisePropertyChanged("fechaSpecified");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=4)]
        public decimal id {
            get {
                return this.idField;
            }
            set {
                this.idField = value;
                this.RaisePropertyChanged("id");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool idSpecified {
            get {
                return this.idFieldSpecified;
            }
            set {
                this.idFieldSpecified = value;
                this.RaisePropertyChanged("idSpecified");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, DataType="integer", Order=5)]
        public string monto {
            get {
                return this.montoField;
            }
            set {
                this.montoField = value;
                this.RaisePropertyChanged("monto");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=6)]
        public usuario usuarioId {
            get {
                return this.usuarioIdField;
            }
            set {
                this.usuarioIdField = value;
                this.RaisePropertyChanged("usuarioId");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    /// <comentarios/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.6.1064.2")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://services/")]
    public partial class deuda : object, System.ComponentModel.INotifyPropertyChanged {
        
        private string costoField;
        
        private System.DateTime fechaField;
        
        private bool fechaFieldSpecified;
        
        private grupo grupoIdField;
        
        private decimal idField;
        
        private bool idFieldSpecified;
        
        private string nombreField;
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, DataType="integer", Order=0)]
        public string costo {
            get {
                return this.costoField;
            }
            set {
                this.costoField = value;
                this.RaisePropertyChanged("costo");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        public System.DateTime fecha {
            get {
                return this.fechaField;
            }
            set {
                this.fechaField = value;
                this.RaisePropertyChanged("fecha");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool fechaSpecified {
            get {
                return this.fechaFieldSpecified;
            }
            set {
                this.fechaFieldSpecified = value;
                this.RaisePropertyChanged("fechaSpecified");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=2)]
        public grupo grupoId {
            get {
                return this.grupoIdField;
            }
            set {
                this.grupoIdField = value;
                this.RaisePropertyChanged("grupoId");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=3)]
        public decimal id {
            get {
                return this.idField;
            }
            set {
                this.idField = value;
                this.RaisePropertyChanged("id");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool idSpecified {
            get {
                return this.idFieldSpecified;
            }
            set {
                this.idFieldSpecified = value;
                this.RaisePropertyChanged("idSpecified");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=4)]
        public string nombre {
            get {
                return this.nombreField;
            }
            set {
                this.nombreField = value;
                this.RaisePropertyChanged("nombre");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    /// <comentarios/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.6.1064.2")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://services/")]
    public partial class grupo : object, System.ComponentModel.INotifyPropertyChanged {
        
        private decimal idField;
        
        private bool idFieldSpecified;
        
        private string nombreField;
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        public decimal id {
            get {
                return this.idField;
            }
            set {
                this.idField = value;
                this.RaisePropertyChanged("id");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool idSpecified {
            get {
                return this.idFieldSpecified;
            }
            set {
                this.idFieldSpecified = value;
                this.RaisePropertyChanged("idSpecified");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        public string nombre {
            get {
                return this.nombreField;
            }
            set {
                this.nombreField = value;
                this.RaisePropertyChanged("nombre");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    /// <comentarios/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.6.1064.2")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://services/")]
    public partial class usuario : object, System.ComponentModel.INotifyPropertyChanged {
        
        private string contrasenaField;
        
        private string emailField;
        
        private decimal idField;
        
        private bool idFieldSpecified;
        
        private string nombreField;
        
        private string numdocumentoField;
        
        private string tipodocumentoField;
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        public string contrasena {
            get {
                return this.contrasenaField;
            }
            set {
                this.contrasenaField = value;
                this.RaisePropertyChanged("contrasena");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        public string email {
            get {
                return this.emailField;
            }
            set {
                this.emailField = value;
                this.RaisePropertyChanged("email");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=2)]
        public decimal id {
            get {
                return this.idField;
            }
            set {
                this.idField = value;
                this.RaisePropertyChanged("id");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool idSpecified {
            get {
                return this.idFieldSpecified;
            }
            set {
                this.idFieldSpecified = value;
                this.RaisePropertyChanged("idSpecified");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=3)]
        public string nombre {
            get {
                return this.nombreField;
            }
            set {
                this.nombreField = value;
                this.RaisePropertyChanged("nombre");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=4)]
        public string numdocumento {
            get {
                return this.numdocumentoField;
            }
            set {
                this.numdocumentoField = value;
                this.RaisePropertyChanged("numdocumento");
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=5)]
        public string tipodocumento {
            get {
                return this.tipodocumentoField;
            }
            set {
                this.tipodocumentoField = value;
                this.RaisePropertyChanged("tipodocumento");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="findByDate", WrapperNamespace="http://services/", IsWrapped=true)]
    public partial class findByDateRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://services/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public System.DateTime date;
        
        public findByDateRequest() {
        }
        
        public findByDateRequest(System.DateTime date) {
            this.date = date;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="findByDateResponse", WrapperNamespace="http://services/", IsWrapped=true)]
    public partial class findByDateResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://services/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute("return", Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public JavaSOAP.ProxyTransaccion.pago[] @return;
        
        public findByDateResponse() {
        }
        
        public findByDateResponse(JavaSOAP.ProxyTransaccion.pago[] @return) {
            this.@return = @return;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="findByDocument", WrapperNamespace="http://services/", IsWrapped=true)]
    public partial class findByDocumentRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://services/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string document;
        
        public findByDocumentRequest() {
        }
        
        public findByDocumentRequest(string document) {
            this.document = document;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="findByDocumentResponse", WrapperNamespace="http://services/", IsWrapped=true)]
    public partial class findByDocumentResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://services/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute("return", Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public JavaSOAP.ProxyTransaccion.pago[] @return;
        
        public findByDocumentResponse() {
        }
        
        public findByDocumentResponse(JavaSOAP.ProxyTransaccion.pago[] @return) {
            this.@return = @return;
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface WSTransactionChannel : JavaSOAP.ProxyTransaccion.WSTransaction, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class WSTransactionClient : System.ServiceModel.ClientBase<JavaSOAP.ProxyTransaccion.WSTransaction>, JavaSOAP.ProxyTransaccion.WSTransaction {
        
        public WSTransactionClient() {
        }
        
        public WSTransactionClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public WSTransactionClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public WSTransactionClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public WSTransactionClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        JavaSOAP.ProxyTransaccion.findByDateResponse JavaSOAP.ProxyTransaccion.WSTransaction.findByDate(JavaSOAP.ProxyTransaccion.findByDateRequest request) {
            return base.Channel.findByDate(request);
        }
        
        public JavaSOAP.ProxyTransaccion.pago[] findByDate(System.DateTime date) {
            JavaSOAP.ProxyTransaccion.findByDateRequest inValue = new JavaSOAP.ProxyTransaccion.findByDateRequest();
            inValue.date = date;
            JavaSOAP.ProxyTransaccion.findByDateResponse retVal = ((JavaSOAP.ProxyTransaccion.WSTransaction)(this)).findByDate(inValue);
            return retVal.@return;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<JavaSOAP.ProxyTransaccion.findByDateResponse> JavaSOAP.ProxyTransaccion.WSTransaction.findByDateAsync(JavaSOAP.ProxyTransaccion.findByDateRequest request) {
            return base.Channel.findByDateAsync(request);
        }
        
        public System.Threading.Tasks.Task<JavaSOAP.ProxyTransaccion.findByDateResponse> findByDateAsync(System.DateTime date) {
            JavaSOAP.ProxyTransaccion.findByDateRequest inValue = new JavaSOAP.ProxyTransaccion.findByDateRequest();
            inValue.date = date;
            return ((JavaSOAP.ProxyTransaccion.WSTransaction)(this)).findByDateAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        JavaSOAP.ProxyTransaccion.findByDocumentResponse JavaSOAP.ProxyTransaccion.WSTransaction.findByDocument(JavaSOAP.ProxyTransaccion.findByDocumentRequest request) {
            return base.Channel.findByDocument(request);
        }
        
        public JavaSOAP.ProxyTransaccion.pago[] findByDocument(string document) {
            JavaSOAP.ProxyTransaccion.findByDocumentRequest inValue = new JavaSOAP.ProxyTransaccion.findByDocumentRequest();
            inValue.document = document;
            JavaSOAP.ProxyTransaccion.findByDocumentResponse retVal = ((JavaSOAP.ProxyTransaccion.WSTransaction)(this)).findByDocument(inValue);
            return retVal.@return;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<JavaSOAP.ProxyTransaccion.findByDocumentResponse> JavaSOAP.ProxyTransaccion.WSTransaction.findByDocumentAsync(JavaSOAP.ProxyTransaccion.findByDocumentRequest request) {
            return base.Channel.findByDocumentAsync(request);
        }
        
        public System.Threading.Tasks.Task<JavaSOAP.ProxyTransaccion.findByDocumentResponse> findByDocumentAsync(string document) {
            JavaSOAP.ProxyTransaccion.findByDocumentRequest inValue = new JavaSOAP.ProxyTransaccion.findByDocumentRequest();
            inValue.document = document;
            return ((JavaSOAP.ProxyTransaccion.WSTransaction)(this)).findByDocumentAsync(inValue);
        }
    }
}
