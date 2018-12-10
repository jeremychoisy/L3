function ask(str) {
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            if (document.getElementById("error") !== null) {
                document.getElementById("error").remove();
            }
            if (document.getElementById("table") !== null){
                                document.getElementById("table").remove();
            }
            if(xmlhttp.responseText == "Error 01"){
                var p = document.createElement("p");
                p.id = "error";
                p.innerText = "Unvalid file/directory";
                document.body.appendChild(p);
                return;

            }
            var listeFichier = JSON.parse(xmlhttp.responseText);
            var table = document.createElement("table");
            table.id = "table";
            table.style = "border:1px solid black;border-collapse:collapse;";
            var tr, td;
            listeFichier.forEach((item) => {
                tr = document.createElement("tr");
                td = document.createElement("td");
                td.innerText = item[0];
                if (item[1] === "dir") {
                    td.onclick = (
                        (function (td) {
                            return function () {
                                ask(str + '/' + td.innerText);
                            };
                        })
                            (td));
                }
                else if (item[1] === "file") {
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
                td.style = "height:50px;border:1px solid black;color:green";
                tr.appendChild(td);
                table.appendChild(tr);
            });
            document.body.appendChild(table);
        }
    }
    xmlhttp.open("GET", "serveur.php?q=" + str, true);
    xmlhttp.send();
}

function displayFile(str) {
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var table = document.getElementById("table");
            var cmpt = document.getElementsByTagName("tr").length;
            if (document.getElementById("content") !== null) {
                document.getElementById("content").remove();
            }
            var td = document.createElement("td");
            var div = document.createElement("div");
            if (str.endsWith(".png") || str.endsWith(".jpg")) {
                var img = document.createElement("img");
                img.src = str;
                div.appendChild(img);
            }
            else if (str.endsWith(".mp3") || str.endsWith(".wav")) {
                console.log(str);
                var audio = document.createElement("audio");
                audio.src = str;
                audio.controls = true;
                div.appendChild(audio);
            }
            else {
                div.innerText = JSON.parse(xmlhttp.responseText);
            }
            div.style = "height:" + (50 * cmpt) + "px;width:1000px;overflow:scroll";
            td.appendChild(div);
            td.style = "width:1000px;border:1px solid black;";
            td.id = "content";
            td.setAttribute("rowspan", cmpt);
            table.firstChild.appendChild(td);
        }
    }
    xmlhttp.open("GET", "serveur.php?q=" + str, true);
    xmlhttp.send();

}
