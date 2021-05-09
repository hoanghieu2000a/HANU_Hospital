import jwt_decode from "jwt-decode";
export function getFormattedDate(date) {
    let year = date.getFullYear();

    let month = (1 + date.getMonth()).toString();
    month = month.length > 1 ? month : '0' + month;

    let day = date.getDate().toString();
    day = day.length > 1 ? day : '0' + day;

    return month + '/' + day + '/' + year;
}

export function searchByIdArray(arr, id) {
    console.log(arr)
    console.log(id)

    return arr.filter((el) => el.id == id)[0]
}

export function checkStillLogin() {
    const token = localStorage.getItem('token');
    if (token) {
        const { exp } = jwt_decode(token);
        const isValid = (((exp * 1000) - Date.now()) > 0);
        return (token && isValid)
    }
    return false;
}

export function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}