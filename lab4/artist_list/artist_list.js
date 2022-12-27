import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayArtist();
});

/**
 * Fetches all artists and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayArtist() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayArtist(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/artist', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display artist.
 *
 * @param {{artist_s: {id:number, name:string}[]}} artist_s
 */
function displayArtist(artist_s) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    artist_s.artist_s.forEach(artist => {
        tableBody.appendChild(createTableRow(artist));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {{id: number, name: string}} artist
 * @returns {HTMLTableRowElement}
 */
function createTableRow(artist) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(artist.name));
    tr.appendChild(createButtonCell('Overview', () => onclick(location.href = '../artist_view/artist_view.html?artist=' + artist.id)));
    tr.appendChild(createButtonCell('Edit', () => onclick(location.href = '../artist_edit/artist_edit.html?artist=' + artist.id)));
    tr.appendChild(createButtonCell('Delete', () => deleteArtist(artist.id)));
    return tr;
}


/**
 * Forwards to artist overall view.
 *
 * @param {number} artist to be viewed
 */
function viewArtist(artist) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayArtist();
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/artist/' + artist, true);
    xhttp.send();
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {number} artist to be deleted
 */
function deleteArtist(artist) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayArtist();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/artist/' + artist, true);
    xhttp.send();
}
