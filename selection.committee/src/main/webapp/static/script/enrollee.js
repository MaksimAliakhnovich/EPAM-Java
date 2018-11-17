// document.getElementById("myForm").action = "/action_page.php";

const xhr = new XMLHttpRequest();
let data = {};
xhr.onreadystatechange = function () {
  if (xhr.readyState == 4 && xhr.status == 200) {
    data = JSON.parse(xhr.response);
    fillHeadEnrolleTable();
    fillBodyEnrolleTable();
  }
};
xhr.open("GET", '/enrollee', true);
xhr.setRequestHeader('Accept', 'application/json;charset=UTF-8');
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
  caption.innerHTML = 'Список абитуриентов'
  tHead.innerHTML =
    `<tr>
    <th>Имя</th>
    <th>Фамилия</th>
    <th>Аттестат</th>
    <th>№ паспорта</th>
    </tr>`;
}

const fillBodyEnrolleTable = () => {
  const tBody = document.querySelector('tbody');
  tBody.innerHTML = data.map(item =>
    `<tr>
      <td>${item.firstName}</td>
      <td>${item.lastName}</td>
      <td>${item.certificateScore}</td>
      <td>${item.passport}</td>
      </tr>`)
    .join('\n');
}

const addEnrolleeButton = document.querySelector('.add');
const addEnrollee = (e) => {
  e.preventDefault();
  const xhr = new XMLHttpRequest();
  const firstName = document.querySelector('input[name=first_name]').value;
  const lastName = document.querySelector('input[name=last_name]').value;
  const score = document.querySelector('input[name=score]').value;
  const passport = document.querySelector('input[name=passport]').value;
  const body = JSON.stringify({
    firstName: firstName,
    lastName: lastName,
    certificateScore: score,
    passport: passport
  });
  xhr.onreadystatechange = function () {
    if (xhr.readyState == 4 && xhr.status == 200) {
      data = JSON.parse(xhr.response);
      fillHeadEnrolleTable();
      fillBodyEnrolleTable();
      console.log(data);
    }
  };
  xhr.open("POST", '/enrollee', true);
  xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
  xhr.send(body);


};
addEnrolleeButton.addEventListener('click', addEnrollee);