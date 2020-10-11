/* Variavel para debug */
var passou_aqui = "passou aqui!!";

// teste de funcao
/**
 * Script para exibicao de contador regressivo 
 */
var sHors = "0" + 0;
var sMins = 19;
var sSecs = 60;
var formulario = "#";

function configTimeout(formularioId, itemT, tMins) {

	formulario = formularioId;

	if (tMins < 10) {
		tMins = "0" + tMins;
	}
	if (tMins > 0) {
		sMins = tMins - 1;
	}

	getSecs(itemT);
};

function getSecs(item) {
	if (sHors == 0 && sMins == 1 && sSecs == 55) {
		sHors = "0" + 0;
		sMins = 19;
		sSecs = 60;
		document.getElementById(formulario).submit();
	}
	if (sSecs <= 9)
		sSecs = "0" + sSecs;
	sSecs = sSecs - 1;
	if (sSecs == 0) {
		sSecs = 59;
		sMins = sMins - 1;
		if (sMins <= 9)
			sMins = "0" + sMins;
	}
	if (sMins == 0) {
		sMins = 59;
		sHors = sHors - 1;
		if (sHors <= 9)
			sHors = "0" + sHors;
	}
	if (sSecs <= 9) {
		sSecs = "0" + sSecs;
	}
	document.getElementById(item).innerHTML = sHors
			+ "<font color=#000000>:</font>" + sMins
			+ "<font color=#000000>:</font>" + sSecs;
	setTimeout('getSecs("' + item + '")', 1000);
};
// =======================
// 

/** Estrutura para os LOGS de atividades: ok */
LOGS = function() {
	var logs = [ {
		"id" : 1,
		"kind" : 1,
		"description" : "#Teste",
		"logged_at" : "01/10/2015 00:12:01"
	}, {
		"id" : 2,
		"kind" : 2,
		"description" : "#teste",
		"logged_at" : "01/10/2015 00:13:02"
	}, {
		"id" : 3,
		"kind" : 3,
		"description" : "#Teste",
		"logged_at" : "01/10/2015 00:13:04"
	} ];
	var vid = 3;

	this.last = function() {
		return this.getValue(vid);
	};

	this.getValue = function(id) {
		var found = 0;
		logs.forEach(function(log) {
			if (log["id"] == id) {
				found = log;
			}
		});
		return found;
	};

	this.del = function(id) {

		var count = 0;

		logs.forEach(function(log) {
			if (log["id"] === id) {
				logs.splice(count, 1);
			}
			count = count + 1;
		});
	};

	this.setLogs = function(kind, description, logged_at) {
		vid++;
		id = vid;
		logs.push({
			"id" : id,
			"kind" : kind,
			"description" : description,
			"logged_at" : logged_at
		});
	};

	this.update = function(id, kind, description, logged_at) {
		for ( var i in logs) {
			if (logs[i].id == id) {
				logs[i].description = description;
				logs[i].logged_at = logged_at;
				logs[i].kind = kind;
				break;
			}
		}
		;
	};

	this.getAll = function() {
		return logs;
	};

	this.show = function() {
		logs.forEach(function(log) {
			console.log(JSON.stringify(log));
		});
		console.log(passou_aqui);
	};
};

/** funcao que retorna a data atual (obvio!): ok  */
var currentDate = function() {
	var today = new Date();
	var min = today.getMinutes();
	var sec = today.getSeconds();
	if (min < 10) {
		min = "0" + min;
	}
	if (sec < 10) {
		sec = "0" + sec;
	}

	dia = today.getDate();
	mes = (today.getMonth() + 1);
	ano = today.getFullYear();

	return dia + "/" + mes + "/" + ano + " " + today.getHours() + ":" + min
			+ ":" + sec;
};

// registros do item a editar
var editar;

// registros do item a done
var did;
var dkind;

/** variavel que indica o tipo de operacao: true edicao(edit) e false feito (done)*/
var edicao = false;

/** Instanciando o banco de dados */
DB = new LOGS;

/** Instanciando variavel: ok */
var logs = DB.getAll();

/** Definindo app: */
var Biohacking = {};

/** Definindo form: */
Biohacking.form = {};

/** Definindo fields: */
Biohacking.form.fields = {};

Biohacking.KIND = {
	11 : "BATH",
	9 : "DEFECATE",
	3 : "DRINK",
	2 : "EAT",
	5 : "HUNGRY",
	10 : "SEX",
	6 : "SLEEP",
	8 : "URINATE",
	7 : "WAKEUP",
	1 : "WEIGHT",
	4 : "WORKOUT"
};

Biohacking.form.fields.Button = function() {
	Biohacking.form.fields.Text.apply(this, arguments);
	this._oldRender = this.render;

	this.setValue = function(id) {
		this.el.setAttribute("value", "Editar:" + id);
	};
	this.setName = function() {
		this.el.setAttribute("value", "Editar");
	};

	this.render = function(field) {
		this._oldRender(field);
		this.name = field.title || field.name;
		this.el.setAttribute("type", "button");
		this.el.setAttribute("class", "form-control btn btn-primary");
		this.el.setAttribute("value", field.title || field.name);
		this.el.setAttribute("name", field.title || field.name);
		this.el.setAttribute("id", field.title || field.name);

		if (field.handler) {
			this.el.addEventListener("click", field.handler);
		}
		if (field.hidden) {
			this.el.style.display = "none";
		}

		return this;
	};

	this.toggle = function() {
		this.el.style.display = (this.el.style.display === "none") ? "flex"
				: "none";
	};
};

Biohacking.form.fields.Date = function() {
	Biohacking.form.fields.Text.apply(this, arguments);
	this._oldRender = this.render;

	this.binding = function(model) {
		var name = this.name;
		var attr = model.attributes[name];

		if (name && attr) {
			this._oldEl.addEventListener("change", function() {
				var json = {};
				json[name] = this.getValue();
				model.setValues(json);
			}.bind(this));

			var json = {};
			json[name] = this.getValue();
			model.setValues(json);
		}
	};

	this.getValue = function() {
		return this._oldEl.value;
	};

	this.setValue = function(valor) {
		this._oldEl.value = valor;
	};

	this.render = function(field) {
		this._oldRender(field);

		this._oldEl = this.el;
		//this._oldEl.value = moment().format("DD/MM/YYYY HH24:mm:ss");
		this._oldEl.value = moment().format("DD/MM/YYYY HH:mm:ss");

		this.el = document.createElement("div");
		this.el.setAttribute("class", "input-group date");

		var glyphicon = document.createElement("span");
		glyphicon.setAttribute("class", "glyphicon glyphicon-calendar");
		var addon = document.createElement("span");
		addon.setAttribute("class", "input-group-addon");
		addon.appendChild(glyphicon);

		this.el.appendChild(this._oldEl);
		this.el.appendChild(addon);
		return this;
	};
};

Biohacking.form.fields.Display = function() {
	Biohacking.form.fields.Field.apply(this, arguments);
	this._oldRender = this.render;
	this.messageArea = document.createElement("div");
	this.hideButton = document.createElement("div");
	this.hideButton.setAttribute("class",
			"glyphicon glyphicon-remove display-hide-button");

	this.setText = function(message) {
		this.messageArea.innerHTML = message;
	};

	this.render = function(field) {
		this._oldRender(field);
		this.el.appendChild(this.hideButton);
		this.el.appendChild(this.messageArea);
		this.setText(field.message);
		return this;
	};
};

/* Linha da tabela: ok */
Biohacking.Row = function() {

	this.el = document.createElement("div");
	this.el.setAttribute("class", "row");

	var createKindValue = function(log) {
		var kindvalue = document.createElement("div");
		kindvalue.setAttribute('class', 'kind');
		kindvalue.setAttribute('id', 'k' + log["id"]);
		kindvalue.setAttribute('name', 'k' + log["id"]);
		kindvalue.innerHTML = Biohacking.KIND[log['kind']];
		return kindvalue;
	};

	var createKindDate = function(log) {
		var kindDate = document.createElement("div");
		kindDate.setAttribute('class', 'date');
		kindDate.setAttribute('id', 'd' + log["id"]);
		kindDate.setAttribute('name', 'd' + log["id"]);
		var aux = log['logged_at'];
		kindDate.innerHTML = aux;
		return kindDate;
	};

	this.getValue = function() {
		return this.el.getAttribute("id");
	};

	this.render = function(log) {
		this.el.setAttribute('id', log['id']);

		var kindinfo = document.createElement("div");
		kindinfo.setAttribute("class", "kind-info");
		kindinfo.appendChild(createKindValue(log));
		kindinfo.appendChild(createKindDate(log));

		var tags = document.createElement("div");
		tags.setAttribute('class', "tags");
		var a = document.createElement("a");
		a.setAttribute('id', 'a' + log["id"]);
		a.setAttribute('name', 'a' + log["id"]);
		a.innerHTML = log['description'];

		tags.appendChild(a);

		this.el.appendChild(kindinfo);
		this.el.appendChild(tags);

		/* funcao para evento da linha: */
		this.el.addEventListener("click", function(evt) {
			evt.preventDefault();
			var id = this.getValue();
			logs.forEach(function(log) {
				if (log["id"] == id) {
					return log;
				}
			});
			editar = log["id"];
			edicao = true;
			formBuilder.displayMessage(Biohacking.KIND[log["kind"]]);
			formBuilder.findField("description").setValue(log["description"]);
			formBuilder.findField("logged_at").setValue(log["logged_at"]);
			formBuilder.findField("Done").toggle();
			/* Escondendo a tabela: */
			formBuilder.findField("tabela").getParent().hide();

			formBuilder.findField("Editar").toggle();
		}.bind(this));
		return this;
	};
};

Biohacking.form.fields.List = function() {
	Biohacking.form.fields.Field.apply(this, arguments);
	this.rows = [];

	this.el = document.createElement("div");
	this.el.setAttribute("class", "list");

	this.update = function(log) {
		var id = log["id"];
		var kind = document.getElementById('k' + id);
		var date = document.getElementById('d' + id);
		var a = document.getElementById('a' + id);
		kind.innerHTML = Biohacking.KIND[log["kind"]];
		date.innerHTML = log["logged_at"];
		a.innerHTML = log["description"];
	};

	this.render = function(field) {
		this.name = field.name;
		this.el.setAttribute("name", field.name);
		this.el.setAttribute("id", field.name);

		logs.forEach(function(log) {
			var row = new Biohacking.Row();
			this.rows.push(row);
			this.el.appendChild(row.render(log).el);
		}, this);
		return this;
	};

	this.addRows = function(log) {
		var row = new Biohacking.Row();
		this.rows.push(row);
		this.el.appendChild(row.render(log).el);
	};

	this.del = function(id) {
		var ro = document.getElementById(id);
		ro.parentNode.removeChild(ro);
		this.rows.splice(ro, 1);
	};
};

Biohacking.form.fields.Field = function() {
	this._listeners = {};
	this.el;
	this.config = {};
	this._parent;

	this.binding = function(model) {
		var json = {};
		json[this.name] = this.getValue();
		model.setValues(json);
	};

	this.getValue = function() {
		return this.el.innerHTML;
	};

	this.setParent = function(component) {
		this._parent = component;
	};

	this.getParent = function() {
		return this._parent;
	};

	this.register = function(events) {
		Object.keys(events).forEach(function(event) {
			this._listeners[event] = events[event];
		}, this);
	};

	this.fireEvent = function(event, options) {
		var listener = this._listeners[event];
		if (listener) {
			listener.handler.call(listener.scope || this, this, options);
		}
	};

	this.render = function(config) {
		this.config = config;
		this.el = document.createElement("div");
		this.name = config.name;

		if (this.name) {
			this.el.setAttribute("name", this.name);
		}
		if (config["class"]) {
			this.el.setAttribute("class", config["class"]);
		}

		return this;
	};

	this.toggle = function() {
		this.el.style.display = (this.el.style.display === "none") ? "flex"
				: "none";
	};
};

Biohacking.form.fields.Text = function() {
	Biohacking.form.fields.Field.apply(this, arguments);
	this.el = document.createElement("input");

	var mandatory = function(event) {
		console.log(event.target.value, event.target.checked);
	};

	this.el.addEventListener("keyup", mandatory.bind(this.el));
	this.el.addEventListener("change", mandatory.bind(this.el));

	this.getValue = function() {
		return this.el.value;
	};

	this.setValue = function(valor) {
		this.el.value = valor;
	};

	this.render = function(field) {
		this.config = field;
		var name = field.id || field.name;
		this.name = name;

		if (name) {
			this.el.setAttribute("id", name);
			this.el.setAttribute("name", name);
		}

		if (field.hidden) {
			this.toggle();
		}

		this.el.setAttribute("class", "form-control field");
		this.el.setAttribute("placeholder", field.placeholder || "Enter text");
		this.el.setAttribute("mandatory", !!field.mandatory);
		return this;
	};
};

Biohacking.form.fields.LookupOption = function() {
	Biohacking.form.fields.Field.apply(this, arguments);
	this.el = document.createElement("div");
	this.selected = false;

	this.getValue = function() {
		return this.el.getAttribute("name");
	};

	this.deselect = function() {
		this.el.setAttribute("class", "list-group-item");
		this.selected = false;
	};

	this.select = function() {
		this.selected = true;
		this.el.setAttribute("class", "list-group-item active");
	};

	this.render = function(config) {
		this.config = config;
		this.el.setAttribute("name", config.key);
		this.el.setAttribute("class", "list-group-item");
		this.el.innerHTML = config.value;

		this.el.addEventListener("click", function(evt) {
			evt.preventDefault();
			this.select();
			this.fireEvent("selected");
		}.bind(this));
		return this;
	};
};

Biohacking.form.fields.Lookup = function() {
	Biohacking.form.fields.Field.apply(this, arguments);
	this.options = [];

	this.getValue = function() {
		var founded = this.options.filter(function(option) {
			return option.selected;
		})[0];
		return (founded) ? founded.getValue() : null;
	};

	this.sorter = function(a, b) {
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
			if (option !== lookupOption)
				option.deselect();
		});
	};

	this.createOption = function(config) {
		var option = new Biohacking.form.fields.LookupOption;
		option.register({
			selected : {
				handler : function(lookupOption) {
					this.deselectAll(lookupOption);
					this.fireEvent("changed", lookupOption);
					this.fireEvent("selected");
				},
				scope : this
			}
		});
		return option.render(config);
	};

	this.binding = function(model) {
		var name = this.name;
		var attr = model.attributes[name];
		if (name && attr) {
			this.register({
				changed : {
					handler : function(field, lookupOption) {
						var json = {};
						json[name] = lookupOption.getValue();
						model.setValues(json);
					},
					scope : this
				}
			});
			var json = {};
			json[name] = this.getValue();
			model.setValues(json);
		}
	};

	this.render = function(config) {
		this.config = config;
		this.el = document.createElement("div");
		this.el.setAttribute("class", "list-group");
		this.name = this.config.name;

		this.options = Object.keys(this.config.options).map(function(key) {
			var conf = {
				key : key,
				value : this.config.options[key]
			};
			return this.createOption(conf);
		}, this);

		this.options.sort(this.sorter).map(function(option) {
			return option.el;
		}).forEach(this.el.appendChild, this.el);
		return this;
	};
};

Biohacking.form.Section = function() {
	this.fields = [];
	this.el = document.createElement("div");
	this.el.setAttribute("class", "section");

	this.createField = function(field) {
		var item = Biohacking.form.fields[field.type]
				|| Biohacking.form.fields.Field;
		var fieldComponent = (new item).render(field);
		fieldComponent.setParent(this);
		return fieldComponent;
	};

	this.hide = function() {
		this.el.style.display = "none";
	};

	this.toggle = function() {
		this.el.style.display = (this.el.style.display === "none") ? "flex"
				: "none";
	};

	this.render = function(section) {
		if (section.hidden)
			this.hide();
		this.fields = section.fields.map(this.createField, this);

		this.fields.forEach(function(field) {
			this.el.appendChild(field.el);
		}, this);
		return this;
	};
};

Biohacking.form.Builder = function() {
	this.layout;
	this.sections = [];
	this._model;
	this.el = document.createElement("form");
	this.afterRender = function() {
		console.log("Original");
	};

	this.createSection = function(section) {
		var sectionComponent = new Biohacking.form.Section;
		sectionComponent.render(section);
		return sectionComponent;
	};

	this.binding = function(field) {
		field.binding(this._model);
	};

	this.setModel = function(model) {
		this._model = model;
		this.sections.forEach(function(section) {
			section.fields.forEach(this.binding, this);
		}, this);
	};

	this.findField = function(fieldName) {
		return this.sections.reduce(function(founded, section) {
			section.fields.forEach(function(field) {
				if (fieldName === field.name)
					founded = field;
			});
			return founded;
		}, null);
	};

	this.getValues = function() {
		return this.sections.reduce(function(values, section) {
			section.fields.forEach(function(field) {
				var value = field.getValue();
				if (value && field.name && field.config.type !== "Button") {
					values[field.name] = value;
				}
			});
			return values;
		}, {});
	};

	this.render = function(layout) {
		if (layout)
			this.layout = layout;
		this.sections = this.layout.sections.map(this.createSection, this);
		var sections = document.createDocumentFragment();
		this.sections.map(function(section) {
			return section.el;
		}).forEach(sections.appendChild, sections);
		this.el.appendChild(sections);
		this.afterRender();
		return this;
	};
};

/* TrackFormBuilder: ok */
Biohacking.TrackFormBuilder = function() {

	this.toggleSections = function() {
		this.sections.forEach(function(section) {
			section.toggle();
		});
	};

	this.resetRef = function() {
		did = 0;
		dkind = 0;
		editar = 0;
	};

	this.displayMessage = function(message) {
		var field = this.findField("display");
		field.setText(message);
	};

	/* destroy formBuilder: ok */
	this.destroy = function() {
		this.toggleSections();
		this.findField("kind").deselectAll();
		this.displayMessage("Item removido com sucesso");
		this.findField("display").getParent().toggle();
		var list = this.findField("tabela");

		if (edicao) {
			DB.del(editar);
			list.del(editar);
			this.findField("Editar").toggle();
			this.findField("Done").toggle();
		} else {
			DB.del(did);
			list.del(did);
		}

		/* resgatando e configurando do banco: */
		logs = DB.getAll();
		DB.show();
		/* escondendo a tabela */
		this.findField("tabela").getParent().hide();
		this.resetRef();
	};

	/* funcao para butao done: ok */
	this.done = function() {
		var ki = this.findField("kind").getValue();
		var de = this.findField("description").getValue();
		var lo = this.findField("logged_at").getValue();

		DB.update(did, parseInt(ki), de, lo);
		/* resgatando e configurando do banco: ? */
		logs = DB.getAll();
		var log = DB.getValue(did);
		var list = this.findField("tabela");
		list.update(log);

		console.log(JSON.stringify(log));

		this.resetRef();

		this.toggleSections();
		this.findField("kind").deselectAll();
		this.displayMessage("Atividade salva com sucesso!");
		this.findField("display").getParent().toggle();
		/* escondendo tabela */
		this.findField("tabela").getParent().hide();
	};

	/* funcao para butao Editar: ok */
	this.editar = function() {
		var ki = this.findField("kind").getValue();
		var de = this.findField("description").getValue();
		var lo = this.findField("logged_at").getValue();

		DB.update(editar, parseInt(ki), de, lo);

		var log = DB.getValue(editar);
		this.findField("tabela").update(log);
		console.log(JSON.stringify(log));
		this.resetRef();

		/* recuperando do banco: ok */
		logs = DB.getAll();

		this.toggleSections();
		this.findField("kind").deselectAll();
		this.findField("Editar").toggle();
		this.findField("Done").toggle();
		this.displayMessage("atualizado com sucesso!");
		/* escondendo display e tabela*/
		this.findField("display").getParent().hide();
		this.findField("tabela").getParent().hide();
	};

	/* funcao para o create: ok*/
	this.create = function() {
		edicao = false;
		/* escondendo display e tabela */
		this.findField("display").getParent().hide();
		this.findField("tabela").getParent().hide();

		var tKind = this.findField("kind").getValue();
		var tDesc = this.findField("description").getValue();
		var tLog = this.findField("logged_at");
		tLog.setValue(currentDate());

		/* salvando dados: */
		DB.setLogs(parseInt(tKind), tDesc, tLog.getValue());
		logs = DB.getAll();
		DB.show();
		var lista = DB.last();
		this.findField("tabela").addRows(lista);
		did = lista["id"];
		dkind = lista["kind"];

		this.toggleSections();
		this.displayMessage(Biohacking.KIND[dkind]);
	};

	this.layout = {

		sections : [ {
			hidden : true,
			fields : [ {
				name : 'display',
				"class" : "display",
				message : 'Atividade salva com sucesso!',
				type : 'Display'
			} ]
		}, {
			fields : [ {
				name : 'kind',
				mandatory : true,
				type : 'Lookup',
				options : Biohacking.KIND
			} ]
		}, {
			hidden : true,
			fields : [ {
				name : 'logged_at',
				mandatory : true,
				type : 'Date'
			} ]
		}, {
			hidden : true,
			fields : [ {
				name : 'description',
				placeholder : "Enter tags to edit",
				type : 'Text'
			} ]
		}, {
			hidden : true,
			fields : [ {
				name : 'Delete',
				type : 'Button',
				handler : this.destroy.bind(this)
			}, {
				name : 'Done',
				type : 'Button',
				handler : this.done.bind(this)
			}, {
				hidden : true,
				name : 'Editar',
				type : 'Button',
				handler : this.editar.bind(this)
			} ]
		}, {
			hidden : true,
			fields : [ {
				name : "tabela",
				type : "List"
			} ]
		} ]
	};

	this.afterRender = function() {
		this.findField("kind").register({
			selected : {
				handler : this.create,
				scope : this
			}
		});
	};
};

Biohacking.TrackFormBuilder.prototype = new Biohacking.form.Builder;

formBuilder = new Biohacking.TrackFormBuilder;

formFilter.appendChild(formBuilder.render(window.layout).el);