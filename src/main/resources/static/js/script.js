// event handling use for handle the event of dark/light theame

//this file execute in every page

console.log("Script loaded");

let currentTheme = getTheme();

// initially khud chalega
changeTheme(currentTheme);


//TODO
function changeTheme(currentTheme){

    // set to web page
    document.querySelector('html').classList.add(currentTheme);

    //set the listener to change theme buttton
    const changeThemeButton = document.querySelector('#theme_change_button');
    //change the text of button
    changeThemeButton.querySelector("span").textContent = currentTheme == 'light' ? 'Dark':'Light';


    changeThemeButton.addEventListener("click",(event) =>{
        const oldTheme = currentTheme;

        console.log("change theme button clicked");
        // remove the current theme
        document.querySelector("html").classList.remove(currentTheme);
        if(currentTheme === "dark")
        {
            currentTheme = "Light";
        }else{
            currentTheme = "dark";
        }
        // update in localstorage
        setTheme(currentTheme);

        //remove the current theme
        document.querySelector("html").classList.remove(oldTheme);

        // set the current theme
        document.querySelector("html").classList.add.apply(currentTheme);

        
    });
}

// set theme to localstorage
function setTheme(theme){
    localStorage.setItem("theme",theme)
}

// get theme from localstorage
function getTheme(){
   let theme=localStorage.getItem("theme");
   if(theme) return theme;
   else return "Light";
}


