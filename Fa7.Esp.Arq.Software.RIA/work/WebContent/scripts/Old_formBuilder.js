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

window.layout = {
   sections:[
    {
      fields: [{
        name: 'kind',
        mandatory: true,
        type: 'combo',
        options: Biohacking.KIND
      }]
    },
    {
      fields: [{
        name: 'logged_at',
        mandatory: true,
        type: 'date'
      },{
        title: 'description',
        type: 'button',
        handler: function(event, field, form){
console.log(arguments);
           
        }
      }]
   },{
     fields: [{
        name: 'description',
        type: 'text',
        hidden: true
     }]
   },{
     fields: [{
        name: 'Add',
        type: 'button'
     }]
   }]
};

function createOption(key) {
    var option = document.createElement("option");
    option.setAttribute("value", key);
    option.innerHTML = this[key];      
    return option;
};

function sorter(a, b){
    if (a.innerHTML > b.innerHTML) {
      return 1;
    }
    if (a.innerHTML < b.innerHTML) {
      return -1;
    }
    return 0;
  };


Biohacking.Fields = {
  combo: function(field){
    var input = document.createElement("select");
    input.setAttribute("class", "form-control field");
    input.setAttribute("placeholder", "Choose");

    Object.keys(field.options)
          .map(createOption, field.options)
          .sort(sorter)
          .forEach(input.appendChild, input);

    return input;
  },

  text: function(field) {

    var input = document.createElement("input");
    input.setAttribute("id", field.id || field.name);
    input.setAttribute("name", field.name);
    input.setAttribute("class", "form-control field");
    input.setAttribute("placeholder", "Enter text");
    input.setAttribute("mandatory", field.mandatory );
    
    var mandatory = function(event) { 
      /* dever de casa */
      console.log(event.target.value, event.target.checked);
    };
    
    input.addEventListener("keyup", mandatory.bind(input) );
    input.addEventListener("change", mandatory.bind(input) );

    return input;
    
  },

  date: function(field) {
    
    var datetimepicker = document.createElement("div");
    datetimepicker.setAttribute("class", "input-group date");
    
    var input = Biohacking.Fields.text.call(this, field);
    input.value = moment().format();
    
    var glyphicon = document.createElement("span");
    glyphicon.setAttribute("class", "glyphicon glyphicon-calendar");
    var addon = document.createElement("span");
    addon.setAttribute("class", "input-group-addon");
    addon.appendChild( glyphicon );
    
    datetimepicker.appendChild( input );
    datetimepicker.appendChild( addon );
   
    return datetimepicker;
  },

button: function(field, form) {
    var input = Biohacking.Fields.text.call(this, field);
        input.setAttribute("type", "button");
        input.setAttribute("class", "form-control");
        input.setAttribute("value", field.name);
        
        input.addEventListener("click", function(evt){
          evt.preventDefault();          
          this.handler(evt, input, form);
        }.bind(field) );
    
    return input;
  }

};

Biohacking.FormBuilder = function(){

  this.layout;
  this.el = document.createElement("form");

  this.createTitle = function(title){
    var area = document.createElement("h1");
    area.innerHTML = title;
    return area;
  };

  this.createField = function(field) {
      var item = Biohacking.Fields[field.type];
      return item(field, this);
  };

  this.createFields = function(fields){
    var fieldsArea = document.createDocumentFragment();
    fields.forEach(function(field){
      fieldsArea.appendChild( this.createField(field)  );
    }, this);
    return fieldsArea;
  };

  this.createSections = function(){
    var sections = document.createDocumentFragment();
    this.layout.sections.forEach(function(section){
      var area = document.createElement("div");
      area.appendChild( this.createFields(section.fields) );
      sections.appendChild(area);
    }, this);
    this.el.appendChild( sections);
  };

  this.render = function(layout){
    this.layout = layout;
    this.createSections();
    return this.el;
  };
}; 

formBuilder = new Biohacking.FormBuilder;

document.body.appendChild(formBuilder.render(layout));