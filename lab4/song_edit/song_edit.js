import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplaySong();
});

/**
 * Fetches songs of the current artist and updates edit form.
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
    xhttp.open("GET", getBackendUrl() + '/api/songs/' + getParameterByName('song'), true);
    xhttp.send();
}

/**
 * Action event handled for updating song info.
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
    xhttp.open("PUT", getBackendUrl() + '/api/songs/' + getParameterByName('song'), true);

    const request = {
        'producer': document.getElementById('producer').value,
        'length': document.getElementById('length').value,
        "releasedate": document.getElementById('releasedate').value,
        'url': document.getElementById('url').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

    finish(getParameterByName('artist'));
}

/**
 * Go back to artist view after updating.
 *
 * @param {number} artist 'parent' artist id
 */
function finish(artist) {
    window.location.href = '../artist_view/artist_view.html?artist=' + artist;
}
