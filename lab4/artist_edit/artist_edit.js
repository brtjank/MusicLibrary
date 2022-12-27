import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplaySong();
});

/**
 * Fetches artist and updates edit form.
 */
function fetchAndDisplaySong() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/artist/' + getParameterByName('artist'), true);
    xhttp.send();
}

/**
 * Action event handled for updating artist info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplaySong();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/artist/' + getParameterByName('artist'), true);

    const request = {
        'genre': document.getElementById('genre').value,
        'nationality': document.getElementById('nationality').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

    finish();
}

/**
 * Go back to artist lis after editing.
 */
function finish() {
    window.location.href = '../artist_list/artist_list.html';
}
