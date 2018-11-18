let data = {};

// Вывод списка абитуриентов
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

// Создание таблицы
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

// Добавление абитуриента
const addEnrolleeButton = document.querySelector('.add');
const firstNameInput = document.querySelector('input[name=first-name]');
const lastNameInput = document.querySelector('input[name=last-name]');
const scoreInput = document.querySelector('input[name=score]');
const passportInput = document.querySelector('input[name=passport]');
const addEnrollee = e => {
  e.preventDefault();
  const xhr = new XMLHttpRequest();
  const firstName = firstNameInput.value;
  const lastName = lastNameInput.value;
  const score = scoreInput.value;
  const passport = passportInput.value;
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

// Создание кнопки
const createButton = (btnName, btnClass) => {
  const button = document.createElement('button');
  button.innerHTML = btnName;
  button.classList.add('button', btnClass);
  return button;
}

// Удаление кнопки
const deleteButton = btnClass => {
  const button = document.querySelector(`.${btnClass}`);
  button ? button.remove() : null;
}

// Поиск абитуриента
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
      main.appendChild(createButton("К списку", "refresh"));
      const refreshEnrolleeButton = document.querySelector('.refresh');
      refreshEnrolleeButton.style.cssText = `position: relative;
      left: 50%;
      transform: translate(-50%, 0);
      `;
      refreshEnrolleeButton.addEventListener('click', () => {
        deleteButton("refresh");
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
    selectRow.classList.remove("-select");
    // selectRow.classList.remove("-edit");
  }
  e.target.parentElement.classList.toggle("-select");
  selectRow ? cancelEdit() : null;
  selectRow = document.querySelector('.-select');

});

// Удаление абитуриента
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
      deleteButton("refresh");
      viewEnrolleeList();
    }
  };
  xhr.open("DELETE", '/enrollee', true);
  xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
  xhr.send(body);
};
deleteEnrolleeButton.addEventListener('click', deleteEnrollee);

// Редактирование абитуриента
const buttonPanel = document.querySelector('.button-panel');
const editEnrolleeButton = document.querySelector('.edit');
const allInput = document.querySelectorAll('input');
const editEnrollee = e => {
  e.preventDefault();
  if (selectRow != null && !selectRow.classList.contains("-edit")) {
    selectRow.classList.add("-edit");
    buttonPanel.appendChild(createButton("Save", "save"));
    buttonPanel.appendChild(createButton("Cancel", "cancel"));
    const saveEnrolleeButton = document.querySelector('.save');
    const cancelEnrolleeButton = document.querySelector('.cancel');
    saveEnrolleeButton.addEventListener('click', saveEnrollee);
    cancelEnrolleeButton.addEventListener('click', cancelEdit);
    allInput.forEach(item => { item.style.backgroundColor = "#dbc547" });
    firstNameInput.value = selectRow.children[0].innerText;
    lastNameInput.value = selectRow.children[1].innerText;
    scoreInput.value = selectRow.children[2].innerText;
    passportInput.value = selectRow.children[3].innerText;
  }
}
editEnrolleeButton.addEventListener('click', editEnrollee);

// Сохранение редактирования
const saveEnrollee = e => {
  e.preventDefault();
  const oldPassport = selectRow.lastElementChild.innerText;
  const firstName = firstNameInput.value;
  const lastName = lastNameInput.value;
  const score = scoreInput.value;
  const passport = passportInput.value;
  const xhr = new XMLHttpRequest();
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
      cancelEdit();
    }
  };
  xhr.open("PUT", '/enrollee', true);
  xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
  xhr.setRequestHeader("Passport", oldPassport);
  xhr.send(body);
}

// Отмена редактирования
const cancelEdit = () => {
  selectRow.classList.remove("-edit");
  deleteButton("save");
  deleteButton("cancel");
  allInput.forEach(item => { item.style.backgroundColor = "#ffffff" });
}
