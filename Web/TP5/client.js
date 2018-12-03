function ask(str) {
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var listeFichier = JSON.parse(xmlhttp.responseText);
            if (document.getElementById("table") !== null){
                document.getElementById("table").remove();
            }
            var table = document.createElement("table");
            table.setAttribute("id","table");
            table.setAttribute("style","border:1px solid black;height:100;")
            var tr,td;
            listeFichier.forEach((item)=>{
               tr = document.createElement("tr");
               td = document.createElement("td");
               td.innerText = item[0];
               if(item[1] === "dir"){
                   td.setAttribute("style","color:green;")
                   td.onclick = (
                       (function (td) {
                           return function () {
                               ask(str + '/' + td.innerText);
                           };
                       })
                           (td));
               }
               else if(item[1] === "file")
               {    
                   td.onclick = (
                       (function (td) {
                           return function () {
                               if (event.button <= 1) {
                                   try {
                                       displayFile(str + '/' + td.innerText);
                                       
                                   } finally {
                                       event.stopPropagation();
                                       event.preventDefault();
                                   }
                               }

                           };
                       })
                           (td));
               }
               tr.appendChild(td);
               tr.appendChild(document.createElement("td"));
               table.appendChild(tr);
            })
            document.body.appendChild(table);
        }
    }
    xmlhttp.open("GET", "serveur.php?q=" + str, true);
    xmlhttp.send();
}

function displayFile(str){
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            console.log(xmlhttp.responseText==="");
            console.log("test");
            var table = document.getElementById("table");
            var cmpt = document.getElementsByTagName("tr").length;
            var td = document.createElement("td");
            console.log("wtf");
            if(str.endsWith(".png") || str.endsWith(".jpg")){
                console.log("image");
                var img = document.createElement("img");
                img.setAttribute("src", str);
                td.appendChild(img);
            }
            else{
                console.log("inner");
                td.innerText = JSON.parse(xmlhttp.responseText);
            }
            td.setAttribute("rowspan",cmpt);
            table.firstChild.appendChild(td);
        } 
    }
    xmlhttp.open("GET", "serveur.php?q=" + str, true);
    xmlhttp.send();

}