let data = {};

const viewEnrolleeList = () => {
  const xhr = new XMLHttpRequest();
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
}
viewEnrolleeList();

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

const caption = document.querySelector('caption');
const tHead = document.querySelector('thead');
const fillHeadEnrolleTable = () => {
  caption.innerHTML = 'Список абитуриентов'
  tHead.innerHTML =
    `<tr>
    <th>Имя</th>
    <th>Фамилия</th>
    <th>Аттестат</th>
    <th>№ паспорта</th>
    </tr>`;
}

const tBody = document.querySelector('tbody');
const fillBodyEnrolleTable = () => {
  tBody.innerHTML = data.map(item =>
    `<tr>
      <td>${item.firstName}</td>
      <td>${item.lastName}</td>
      <td>${item.certificateScore}</td>
      <td>${item.passport}</td>
      </tr>`)
    .join('\n');
}

// Добавление
const addEnrolleeButton = document.querySelector('.add');
const addEnrollee = e => {
  e.preventDefault();
  const xhr = new XMLHttpRequest();
  const firstName = document.querySelector('input[name=first-name]').value;
  const lastName = document.querySelector('input[name=last-name]').value;
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
    }
  };
  xhr.open("POST", '/enrollee', true);
  xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
  xhr.setRequestHeader("Type", "add");
  xhr.send(body);
};
addEnrolleeButton.addEventListener('click', addEnrollee);

// Кнопка "обновить"
const button = document.createElement('button');
button.innerHTML = "К списку";
button.classList.add('button', 'refresh');
button.style.cssText = `position: relative;
  left: 50%;
  transform: translate(-50%, 0);
`;

// Поиск
const findEnrolleeButton = document.querySelector('.find');
const findEnrollee = e => {
  e.preventDefault();
  const xhr = new XMLHttpRequest();
  const passport = document.querySelector('input[name=passport]').value;
  const body = JSON.stringify({
    passport: passport
  });
  xhr.onreadystatechange = function () {
    if (xhr.readyState == 4 && xhr.status == 200) {
      data = Array.of(JSON.parse(xhr.response));
      fillHeadEnrolleTable();
      fillBodyEnrolleTable();
      main.appendChild(button);
      const refreshEnrolleeButton = document.querySelector('.refresh');
      refreshEnrolleeButton.addEventListener('click', () => {
        main.removeChild(refreshEnrolleeButton);
        viewEnrolleeList();
      });
    }
  };
  xhr.open("POST", '/enrollee', true);
  xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
  xhr.setRequestHeader("Type", "find");
  xhr.send(body);
};
findEnrolleeButton.addEventListener('click', findEnrollee);

//Выделение строки таблицы
let selectRow = null;
tBody.addEventListener('click', e => {
  if (selectRow && e.target.parentElement != selectRow) {
    selectRow.classList.remove('-select');
  }
  e.target.parentElement.classList.toggle("-select");
  selectRow = document.querySelector('.-select');
});

// Удаление
const deleteEnrolleeButton = document.querySelector('.delete');
const deleteEnrollee = e => {
  e.preventDefault();
  const xhr = new XMLHttpRequest();
  const passport = selectRow.lastElementChild.innerText;
  const body = JSON.stringify({
    passport: passport
  });
  xhr.onreadystatechange = function () {
    if (xhr.readyState == 4 && xhr.status == 200) {
      viewEnrolleeList();
    }
  };
  xhr.open("DELETE", '/enrollee', true);
  xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
  xhr.send(body);
};
deleteEnrolleeButton.addEventListener('click', deleteEnrollee);
