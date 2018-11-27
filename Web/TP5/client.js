function ask(str) {
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            let listeFichier = JSON.parse(xmlhttp.responseText);
            if (document.getElementById("table") !== null){
                document.getElementById("table").remove();
            }
            var table = document.createElement("table");
            table.setAttribute("id","table");
            var tr;
            listeFichier.forEach((item)=>{
               tr = document.createElement("tr");
               tr.innerText = item[0];
               if(item[1] == "dir"){
                   tr.setAttribute("style","color:green;")
               }
               table.appendChild(tr);
            })
            document.body.appendChild(table);
        }
    }
    xmlhttp.open("GET", "serveur.php?q=" + str, true);
    xmlhttp.send();
}