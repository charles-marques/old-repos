/**
 * Deprecated
 */
Biohacking = {};

Biohacking.KIND = {
  11: "BATH",
  9: "DEFECATE",
  3: "DRINK",
  2: "EAT",
  5: "HUNGRY",
  10: "SEX",
  6: "SLEEP",
  8: "URINATE",  
  7: "WAKEUP",    
  1: "WEIGHT",
  4: "WORKOUT"
};

Biohacking.Fields = {};

Biohacking.Fields.Field = function() {
  
  this._listeners = {};
  this.el;
  
  this.register = function( events ) {
    Object.keys(events).forEach(function(event) {
      this._listeners[event] = events[event];
    }, this);
  };
  
  this.fireEvent = function(event) {
    var listener = this._listeners[event];
    if( listener ) {
      listener.handler.call( listener.scope || this, this );
    }
  };
  
  this.render = function(config){
    this.name = config.name;
    this.el = document.createElement("div");
    return this;
  };
  
  this.toggle = function() {
    this.el.style.display = (this.el.style.display === "none")? "flex":"none";
  };
  
};

Biohacking.Fields.LookupOption = function() {
  
  Biohacking.Fields.Field.apply(this, arguments);
      
  this.el = document.createElement("li");
  
  this.deselect = function() {
    this.el.setAttribute("class", "list-group-item");
  };
  
  this.select = function() {
    this.el.setAttribute("class", "list-group-item active");
  };
    
  this.render = function(config) {
    
    this.el.setAttribute("name", config.key);
    this.el.setAttribute("class", "list-group-item");
    this.el.innerHTML = config.value;

    this.el.addEventListener("click", function(evt){
      evt.preventDefault();          
      this.select();
      this.fireEvent("selected");
    }.bind(this) );
    
    return this;
    
  };
  
};


Biohacking.Fields.Lookup = function() {
  Biohacking.Fields.Field.apply(this, arguments);
  
  this.options = [];
  
  this.sorter = function(a, b){
    if (a.el.innerHTML > b.el.innerHTML) {
      return 1;
    }
    if (a.el.innerHTML < b.el.innerHTML) {
      return -1;
    }
    return 0;
  };
  
  this.deselectAll = function(lookupOption) {
    this.options.forEach(function(option) {
      if(option !== lookupOption) option.deselect();
    });
  };
  
  this.createOption = function(config) {
    var option = new Biohacking.Fields.LookupOption;
    option.register({
      selected: {
        handler: function(lookupOption) {
          this.deselectAll(lookupOption);
          this.fireEvent("selected");
        },
        scope: this
      }
    });
    return option.render(config); 
  };
  
  this.render = function(config){
    this.el = document.createElement("ul");
    this.el.setAttribute("class", "list-group");
    
    this.options = Object.keys(config.options).map(function(key){
      var conf = { 
        key: key, 
        value: config.options[key]
      };
      return this.createOption(conf);
    }, this);
    
    this.options.sort(this.sorter)
                .map(function(option){ return option.el; })
                .forEach(this.el.appendChild, this.el);
          
    return this;
  };
  
};

Biohacking.Fields.Text = function() {
  Biohacking.Fields.Field.apply(this, arguments);
  
  this.el = document.createElement("input");
  
  var mandatory = function(event) { 
    /* dever de casa */
    console.log(event.target.value, event.target.checked);
  };
  
  this.el.addEventListener("keyup", mandatory.bind(this.el) );
  this.el.addEventListener("change", mandatory.bind(this.el) );
  
  this.render = function(field) {
    
    if(field.hidden) {
      this.toggle();
    }
    
    var name = field.id || field.name;
    this.name = name;
    
    if(name) { 
      this.el.setAttribute("id", name);
      this.el.setAttribute("name", name);
    }
    this.el.setAttribute("class", "form-control field");
    this.el.setAttribute("placeholder", "Enter text");
    this.el.setAttribute("mandatory", !!field.mandatory );
    return this;
  };
  
};

Biohacking.Fields.Button = function() {
  
  Biohacking.Fields.Text.apply(this, arguments);
  
  this._oldRender = this.render;
  
  this.render = function(field) {
    this._oldRender(field);
    this.el.setAttribute("type", "button");
    this.el.setAttribute("class", "form-control");
    this.el.setAttribute("value", field.title || field.name);
    return this;
  };
  
};


Biohacking.Fields.Date = function() {
  Biohacking.Fields.Text.apply(this, arguments);
  
  this._oldRender = this.render;
  
  this.render = function(field) {
    this._oldRender(field);
    
    this._oldEl = this.el;
    this._oldEl.value = moment().format();
        
    this.el = document.createElement("div");
    this.el.setAttribute("class", "input-group date");
    
    var glyphicon = document.createElement("span");
    glyphicon.setAttribute("class", "glyphicon glyphicon-calendar");
    var addon = document.createElement("span");
    addon.setAttribute("class", "input-group-addon");
    addon.appendChild( glyphicon );
    
    this.el.appendChild( this._oldEl );
    this.el.appendChild( addon );

    return this;
  };
};

Biohacking.Section = function() {
  this.fields = [];
  this.el = document.createElement("div");
  this.el.setAttribute("class", "section");
  
  this.createField = function(field) {
      var item = Biohacking.Fields[field.type] || Biohacking.Fields.Field;
      return (new item).render(field);
  };
  
  this.render = function(section) {
    this.fields = section.fields.map(this.createField, this);
    this.fields.forEach(function(field){
      this.el.appendChild( field.el );
    }, this);
    return this;
  };
  
};

Biohacking.FormBuilder = function(){

  this.layout;
  this.sections = [];
  this.el = document.createElement("form");
  this.afterRender = function(){ console.log("Original"); };

  this.createSection = function(section){
      var sectionComponent = new Biohacking.Section;
      sectionComponent.render(section);
      return sectionComponent;
  };
  
  this.findField = function(fieldName) {
    
    return this.sections.reduce(function(founded, section){
      section.fields.forEach(function(field){
        if(fieldName === field.name) founded = field;
      });
      return founded;
    }, null);
    
  };

  this.render = function(layout){
    if(layout) this.layout = layout;
    this.sections = this.layout.sections.map( this.createSection, this );
    var sections = document.createDocumentFragment();
    this.sections.map(function(section){ return section.el; })
                 .forEach( sections.appendChild, sections );
    this.el.appendChild( sections );
    this.afterRender();
    return this;
  };
  
};

Biohacking.FormBuilderA = function() {
  
  this.layout = {

     sections:[
      {
        fields: [{
          name: 'kind',
          mandatory: true,
          type: 'Lookup',
          options: Biohacking.KIND
        }]
      },
      {
        fields: [{
          name: 'logged_at',
          mandatory: true,
          type: 'Date'
        },{
          title: 'description',
          name: 'buttondescription',
          type: 'Button',
          handler: function(event, field, form){ 

          }
        }]
     },{
       fields: [{
          name: 'description',
          type: 'Text',
          hidden: true
       }]
     },{
       fields: [{
          name: 'Add',
          type: 'Button'
       }]
     }]
  };
  
  this.afterRender = function() {
    var input = this.el.querySelector("#buttondescription");
    var description = this.findField("description");
    input.addEventListener("click", function(evt){
      evt.preventDefault();          
      description.toggle();
    }.bind(this) );
  };
  
};
Biohacking.FormBuilderA.prototype = new Biohacking.FormBuilder;