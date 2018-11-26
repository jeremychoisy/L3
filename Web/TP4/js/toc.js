
window.onload = toc;

function toc(){
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
  table.setAttribute("style","float:right;border:1px solid black;");
  document.body.insertBefore(table,firstChild);
}

function addEventListeners(node,tagname){
  if(tagname === 'H1'){
    var backgroundColor = 'background-color:red;';
  }else{
    var backgroundColor = 'background-color:green;';
  }
  node.addEventListener('mouseover', function(event){
  document.getElementById(this.firstChild.getAttribute("href").slice(1)).setAttribute("style",backgroundColor);
  },false);

  node.addEventListener('mouseleave', function(event){
  document.getElementById(this.firstChild.getAttribute("href").slice(1)).removeAttribute("style",backgroundColor);
  },false);
}
