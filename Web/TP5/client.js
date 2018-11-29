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
                   tr.onclick = (
                       (function (tr) {
                           return function () {
                               ask(str + '/' + tr.innerText);
                           };
                       })
                           (tr));
               }
               else
               {
                   tr.onclick = (
                       (function (tr) {
                           return function () {
                               if (event.button <= 1) {
                                   try {
                                       displayFile(str + '/' + tr.innerText);
                                   } finally {
                                       event.stopPropagation();
                                       event.preventDefault();
                                   }
                               }

                           };
                       })
                           (tr));
               }
               table.appendChild(tr);
            })
            document.body.appendChild(table);
        }
    }
    xmlhttp.open("GET", "serveur.php?q=" + str, true);
    xmlhttp.send();
}