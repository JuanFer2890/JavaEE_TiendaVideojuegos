function validar() 
{
	var n1, n2, n3;
	n1 = document.getElementById("CVE").value;
	n2 = document.getElementById("TIT").value;
	n3 = document.getElementById("PRE").value;
	n4 = document.getElementById("CVEPROV").value;
	n5 = document.getElementById("INV").value;
	if(n1=="" ||n2=="" ||n3=="" ||n4=="" ||n5=="")
	{
		alert("Los campos no pueden quedar vacios");
	}	
	else
	{
		console.log("se van a enviar los campos");
		document.forms[0].action = "InsertarVideojuego.jsp?CVE="+n1+"&TIT="+n2+"&PRE="+n3+"&CVEPROV="+n4+"&INV="+n5;
		document.forms[0].method = "post";
		document.forms[0].submit();
		console.log("campos enviados!");
		alert("Videojuego registrado!");
	}
}

function validarProv() 
{
	var n1, n2, n3;
	n1 = document.getElementById("CVE").value;
	n2 = document.getElementById("NOM").value;
	n3 = document.getElementById("EMAIL").value;
	n4 = document.getElementById("TEL").value;
	if(n1=="" ||n2=="" ||n3=="" ||n4=="")
	{
		alert("Los campos no pueden quedar vacios");
	}	
	else
	{
		document.forms[0].action = "InsertarProveedor.jsp?CVE="+n1+"&NOM="+n2+"&EMAIL="+n3+"&TEL="+n4;
		document.forms[0].method = "post";
		document.forms[0].submit();
		alert("Proveedor registrado!");
	}
}

function actualizar() 
{
	var n1, n2, n3, n4, n5;
	n1 = document.getElementById("CVE").value;
	n2 = document.getElementById("TIT").value;
	n3 = document.getElementById("PRE").value;
	n4 = document.getElementById("CVEPROV").value;
	n5 = document.getElementById("INV").value;
	if(n1=="" ||n2=="" ||n3=="" ||n4=="" ||n5=="")
	{
		alert("Los campos no pueden quedar vacios");
	}	
	else
	{
		document.forms[0].action = "InsertarVideojuego.jsp?CVE="+n1+"&TIT="+n2+"&PRE="+n3+"&CVEPROV="+n4+"&INV="+n5+"&MOD=True";
		document.forms[0].method = "post";
		document.forms[0].submit();
		alert("Videojuego actualizado!");
	}
}

function alertar()
{
	alert("Si entro a la funcion");
}

function anadirComboBox(proveedor)
{
	alert("a");
	alert(proveedor);
	var x = document.getElementById("proveedores");
    var yaSeEncuentra = false;
    var i;
    
    //recorre todas las opciones del bomboBox
    for (i = 0; i < x.length; i++) 
    {
		//checa si el parametro recibido ya existe en el comboBox
		if(x.options[i].text.equals(proveedor))
		{
			yaSeEncuentra = true;
		}
    }
    
    //si no se encuentra, añadirlo
    if(yaSeEncuentra==false)
    {
		var option = document.createElement("option");
		option.text = proveedor;
		//x.add(option);
	}
	
}
