import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => addSongAction(event));
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
 * Action event handled for adding new song.
 * @param {Event} event dom event
 */
function addSongAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();

    xhttp.open("POST", getBackendUrl() + '/api/songs', true);

    const request = {
        'name': document.getElementById('name').value,
        'artist': getParameterByName('artist'),
        'producer': document.getElementById('producer').value,
        'length': document.getElementById('length').value,
        'releasedate': document.getElementById('releasedate').value,
        'url': document.getElementById('url').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            var res = JSON.parse(xhttp.response);
            console.log(res);
        }
    };

    xhttp.send(JSON.stringify(request));

    finish(getParameterByName('artist'));
}

/**
 * Go back to artist view after adding.
 *
 * @param {number} artist 'parent' artist id
 */
function finish(artist) {
    window.location.href = '../artist_view/artist_view.html?artist=' + artist;
}
