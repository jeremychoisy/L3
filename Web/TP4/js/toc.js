
window.onload = toc_4;


function toc_3(){
  var table = document.createElement("table");
  var titles = document.getElementsByTagName("h1");
  var link,cell;
  for(var i=0;i<titles.length;i++){
    cell = document.createElement("tr");
    link = document.createElement("a");
    if(titles[i].id === "") {
      titles[i].setAttribute("id",titles[i].innerText);
    }
    link.setAttribute("href","#"+titles[i].id);
    link.innerText = titles[i].innerText;
    cell.appendChild(link);

    cell.addEventListener('mouseover', function(event){
    document.getElementById(this.firstChild.getAttribute("href").substr(1,this.firstChild.getAttribute("href").length-1)).setAttribute("style","background-color:red;");
    },false);

    cell.addEventListener('mouseleave', function(event){
    document.getElementById(this.firstChild.getAttribute("href").substr(1,this.firstChild.getAttribute("href").length-1)).removeAttribute("style","background-color:red;");
    },false);

    table.appendChild(cell);
  }
  var firstChild = document.body.firstChild;
  document.body.insertBefore(table,firstChild);
}

function toc_4(){
  var link,cell;
  var table = document.createElement("table");
  var elem = document.body.firstChild;

  while(elem){
    if(elem.tagName === 'H1' || elem.tagName === 'H2'){
      cell = document.createElement("tr");
      link = document.createElement("a");
      if(elem.id === "") {
        elem.setAttribute("id",elem.innerText);
      }
      link.setAttribute("href","#"+elem.id);
      link.innerText = elem.innerText;
      cell.appendChild(link);
      addEventListeners(cell,elem.tagName);
      table.appendChild(cell);
    }
    elem = elem.nextSibling;
  }
  var firstChild = document.body.firstChild;
  document.body.insertBefore(table,firstChild);
}

function addEventListeners(node,tagname){
  if(tagname === 'H1'){
    var backgroundColor = 'background-color:red;';
  }else{
    var backgroundColor = 'background-color:green;';
  }
  node.addEventListener('mouseover', function(event){
  document.getElementById(this.firstChild.getAttribute("href").substr(1,this.firstChild.getAttribute("href").length-1)).setAttribute("style",backgroundColor);
  },false);

  node.addEventListener('mouseleave', function(event){
  document.getElementById(this.firstChild.getAttribute("href").substr(1,this.firstChild.getAttribute("href").length-1)).removeAttribute("style",backgroundColor);
  },false);
}
