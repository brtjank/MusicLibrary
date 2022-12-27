import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayArtist();
    fetchAndDisplaySongs();
});

/**
 * Fetches all songs and modifies the DOM tree in order to display them.
 */
function fetchAndDisplaySongs() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySongs(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/artist/' + getParameterByName('artist') + '/songs', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display songs.
 *
 * @param {{songs: {id: number, name:string}[]}} songs
 */
function displaySongs(songs) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    songs.songs.forEach(song => {
        tableBody.appendChild(createTableRow(song));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {{id: number, name: string}} song
 * @returns {HTMLTableRowElement}
 */
function createTableRow(song) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(song.name));
    tr.appendChild(createButtonCell('Overview', () => onclick(location.href = '../song_view/song_view.html?artist=' 
        + getParameterByName('artist') + '&song=' + song.id)));
    tr.appendChild(createButtonCell('Edit', () => onclick(location.href = '../song_edit/song_edit.html?artist='
        + getParameterByName('artist') + '&song=' + song.id)));
    tr.appendChild(createButtonCell('Delete', () => deleteSong(song.id)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {number} song to be deleted
 */
function deleteSong(song) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplaySongs();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/songs/' + song, true);
    xhttp.send();
}

/**
 * Go to Add Song form under the artist id.
 *
 * @param {number} artist 'parent' artist id
 */
function addSong(artist) {
    window.location.href = '../song_add/song_add.html?artist=' + artist;
}

/**
 * Fetches single artist and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayArtist() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayArtist(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/artist/' + getParameterByName('artist'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display artists.
 *
 * @param {{name: string, genre: string, nationality:string}} artist
 */
function displayArtist(artist) {
    setTextNode('name', artist.name);
    setTextNode('genre', artist.genre);
    setTextNode('nationality', artist.nationality);

    var button = document.createElement("button");
    button.innerHTML = "Add Song";
    button.className = "ui-control ui-button";
    button.id = "submit" 
    var body = document.getElementById('add-button');
    body.appendChild(button);
    button.addEventListener ("click", () => addSong(getParameterByName('artist')));
}
