import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => addArtistAction(event));
});

/**
 * Action event handled for adding new artist.
 * @param {Event} event dom event
 */
function addArtistAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();

    xhttp.open("POST", getBackendUrl() + '/api/artist', true);

    const request = {
        'name': document.getElementById('name').value,
        'genre': document.getElementById('genre').value,
        'nationality': document.getElementById('nationality').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            var res = JSON.parse(xhttp.response);
            console.log(res);
        }
    };

    xhttp.send(JSON.stringify(request));

    finish();
}

/**
 * Go back to artist list after adding.
 */
function finish() {
    window.location.href = '../artist_list/artist_list.html';
}
