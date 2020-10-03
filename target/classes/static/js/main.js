var vision = true;

function openBar() {
	var bar = document.getElementById("mobile-bar");
	if(vision){
		bar.setAttribute("style", "position: initial; transform: translateX(0);");
		vision = false;
	} else {
		bar.setAttribute("style", "position: absolute; transform: translateX(420);");
		vision = true;
	}
}