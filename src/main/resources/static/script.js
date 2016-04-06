function validateLoginForm() {
    var email = document.forms["loginForm"]["email"].value;
    var password = document.forms["loginForm"]["password"].value;
    var atpos = email.indexOf("@");
    var dotpos = email.indexOf(".");
    if (email == "" && password == "") {
        document.getElementById("message").innerHTML = "<span class='error_text'>Please enter your e-mail and password.</span>";
        document.loginForm.email.focus();
        return false;
    } else if (email == "") {
        document.getElementById("message").innerHTML = "<span class='error_text'>Please enter your e-mail.</span>";
        document.loginForm.email.focus();
        return false;
    } else if (password == "") {
        document.getElementById("message").innerHTML = "<span class='error_text'>Please enter your password.</span>";
        document.loginForm.password.focus();
        return false;
    } else if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length) {
        document.getElementById("message").innerHTML = "<span class='error_text'>Please enter valid e-mail.</span>";
        document.loginForm.email.focus();
        return false;
    } else {
        document.getElementById("message").innerHTML = "";
        return true;
    }
}

function validateRegisterForm() {
    var email = document.forms["registerForm"]["email"].value;
    var password = document.forms["registerForm"]["password"].value;
    var reentered = document.forms["registerForm"]["reentered"].value;
    var atpos = email.indexOf("@");
    var dotpos = email.indexOf(".");
    if (email == "" && password == "" && reentered == "") {
        document.getElementById("error").innerHTML = "<span class='error_text'>Please fill in empty fields.</span>";
        document.registerForm.email.focus();
        return false;
    } else if (email == "") {
        document.getElementById("error").innerHTML = "<span class='error_text'>Please enter your e-mail.</span>";
        document.registerForm.email.focus();
        return false;
    } else if (password == "") {
        document.getElementById("error").innerHTML = "<span class='error_text'>Please enter your password.</span>";
        document.registerForm.password.focus();
        return false;
    } else if (reentered == "" || password != reentered) {
        document.getElementById("error").innerHTML = "<span class='error_text'>Entered passwords do not match.</span>";
        document.registerForm.password.focus();
        return false;
    } else if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length) {
        document.getElementById("error").innerHTML = "<span class='error_text'>Please enter valid e-mail.</span>";
        document.registerForm.email.focus();
        return false;
    } else {
        document.getElementById("error").innerHTML = "";
        return true;
    }
}

function validateAuthorLoginForm() {
    var authorId = document.forms["authorLoginForm"]["author_id"].value;
    var password = document.forms["authorLoginForm"]["password"].value;
    if (authorId == "" && password == "") {
        document.getElementById("errmsg").innerHTML = "<span class='error_text'>Please enter your login and password.</span>";
        document.authorLoginForm.author_id.focus();
        return false;
    } else if (authorId == "") {
        document.getElementById("errmsg").innerHTML = "<span class='error_text'>Please enter your login.</span>";
        document.authorLoginForm.author_id.focus();
        return false;
    } else if (password == "") {
        document.getElementById("errmsg").innerHTML = "<span class='error_text'>Please enter your password.</span>";
        document.authorLoginForm.password.focus();
        return false;
    } else {
        document.getElementById("errmsg").innerHTML = "";
        return true;
    }
}

function emptyDate() {document.getElementById("nullDate").innerHTML = "<span class='error_text'>Select the date.</span>";}
function emptyUrl() {document.getElementById("nullUrl").innerHTML = "<span class='error_text'>Enter the URL.</span>";}
function emptyTitle() {document.getElementById("nullTitle").innerHTML = "<span class='error_text'>Enter the title.</span>";}
function emptyShortTitledId() {document.getElementById("nullShortTitle").innerHTML = "<span class='error_text'>Enter the short title.</span>";}
function emptyExtract() {document.getElementById("nullExtract").innerHTML = "<span class='error_text'>Fill in the extract.</span>";}
function emptyContent() {document.getElementById("nullContent").innerHTML = "<span class='error_text'>Fill in the contet.</span>";}

function filledDate() {document.getElementById("nullDate").innerHTML = "";}
function filledUrl() {document.getElementById("nullUrl").innerHTML = "";}
function filledTitle() {document.getElementById("nullTitle").innerHTML = "";}
function filledShortTitledId() {document.getElementById("nullShortTitle").innerHTML = "";}
function filledExtract() {document.getElementById("nullExtract").innerHTML = "";}
function filledContent() {document.getElementById("nullContent").innerHTML = "";}

function validatePublicationForm() {
    var date = document.getElementById("date").value;
    var url = document.getElementById("url").value;
    var title = document.forms["publicationForm"]["title"].value;
    var shortTitledId = document.forms["publicationForm"]["shortTitledId"].value;
    var extract = document.getElementById("extract").value;
    var content = tinyMCE.get("content").getContent();

    if (date == "") {
        emptyDate(); filledUrl(); filledTitle(); filledShortTitledId(); filledExtract(); filledContent();
        return false;
    }
    if (url == "") {
        filledDate(); emptyUrl(); filledTitle(); filledShortTitledId(); filledExtract(); filledContent();
        return false;
    }
    if (title == "") {
        filledDate(); filledUrl(); emptyTitle(); filledShortTitledId(); filledExtract(); filledContent();
        return false;
    }
    if (shortTitledId == "") {
        filledDate(); filledUrl(); filledTitle(); emptyShortTitledId(); filledExtract(); filledContent();
        return false;
    }
    if (extract == "") {
        filledDate(); filledUrl(); filledTitle(); filledShortTitledId(); emptyExtract(); filledContent();
        return false;
    }
    if (content == "") {
        filledDate(); filledUrl(); filledTitle(); filledShortTitledId(); filledExtract(); emptyContent();
        return false;
    }

    return true;
}
