/**
 * Deprecated
 */
Biohacking.EventListener = function(){
  this._listeners = {};
  
  this.register = function( events ) {
    Object.keys(events).forEach(function(event) {
      this._listeners[event] = events[event];
    }, this);
  };
  
  this.fireEvent = function(event, options) {
    var listener = this._listeners[event];
    if( listener ) {
      listener.handler.call( listener.scope || this, this, options );
    }
  };
};


Biohacking.LogCreateForm = function() {

  var logsController = new LogsController;

  this.create = function() {

      logsController.create( this._model.getValues(), function(xhr) {
         var json = JSON.parse(xhr.responseText);
         this.fireEvent("created", json );
      }.bind(this));
      
  };

  this.layout = {

     sections:[
      {
        fields: [{
          name: 'kind',
          mandatory: true,
          type: 'Lookup',
          options: Biohacking.KIND
        }]
      }]
  };


  this.afterRender = function() {
    
    this.findField("kind").register({
      selected: {
        handler: this.create,
        scope: this
      }
    });
    
    var model = new Biohacking.LogModel;
    model.reset();
    this.setModel(model);
    
  };

};

Biohacking.LogCreateForm.prototype = new Biohacking.form.Builder;


Biohacking.LogList = function() {

  Biohacking.EventListener.call(this);

  this.el = document.createElement("div");
  this.toolbar = document.createElement("div");
  
  this.newLog = document.createElement("input");
  this.newLog.setAttribute("type", "button");
  this.newLog.setAttribute("value","new");
  this.newLog.addEventListener("click", function(){
     this.fireEvent("new");
  }.bind(this));

  this.toolbar.appendChild(this.newLog);
  this.el.appendChild(this.toolbar);
  
  this.panel = document.createElement("div");
  this.el.appendChild(this.panel);
  
  this.addRow = function(log) {
    var row = document.createElement("div");

    row.innerHTML = Biohacking.KIND[log.kind];
    this.panel.appendChild(row);
  };
};


Biohacking.App = function(){


  this.newLog = function(){
      this._logList.hide();
      this._formBuilder.show();
  };  

  this._logList = new Biohacking.LogList;
  this._logList.register({
    "new": {
      handler: this.newLog, scope: this
    }
  });


  this.created = function(form, log){
     this._logList.addRow(log);
     this._formBuilder.hide();
  };

  this._formFilter = document.querySelector(".filter");

  this._formBuilder = new Biohacking.LogCreateForm;
  this._formBuilder.render();
  this._formBuilder.register({
    created: {
      handler: this.created, scope: this
    }
  });
  
  this._formFilter.appendChild( this._formBuilder.el );
  this._formFilter.appendChild( this._logList.el );

};

app = new Biohacking.App();