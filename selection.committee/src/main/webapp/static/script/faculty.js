const xhr = new XMLHttpRequest();
// const body = '{ "table": "faculty" }';
let data = {};
xhr.onreadystatechange = function () {
  if (xhr.readyState == 4 && xhr.status == 200) {
    data = JSON.parse(xhr.response);
    fillHeadEnrolleTable();
    fillBodyEnrolleTable();
  }
};
xhr.open("GET", '/faculty', true);
xhr.setRequestHeader('Accept', 'application/json;charset=UTF-8');
// xhr.send(body);
xhr.send();

const createTable = () => {
  const newTable = document.createElement('table');
  newTable.innerHTML =
    `<caption></caption>
    <thead></thead>
    <tbody></tbody>`;
  return newTable;
}

const main = document.querySelector('.main');
main.appendChild(createTable());

const fillHeadEnrolleTable = () => {
  const caption = document.querySelector('caption');
  const tHead = document.querySelector('thead');
  caption.innerHTML = 'Список факультетов'
  tHead.innerHTML =
    `<tr>
    <th>Факультет</th>
    <th>План набора</th>
    </tr>`;
}

const fillBodyEnrolleTable = () => {
  const tBody = document.querySelector('tbody');
  tBody.innerHTML = data.map(item =>
    `<tr>
      <td>${item.name}</td>
      <td>${item.plan}</td>
      </tr>`)
    .join('\n');
}


