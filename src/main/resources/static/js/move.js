const form = document.querySelector("#allForm");

form.addEventListener("submit", (e) => {
  e.preventDefault();
  // check 요소 가져오기
  const allChecks = form.querySelectorAll("input[type='checkbox']:checked");
  let result = "";
  allChecks.forEach((check, idx) => {
    // form 체크된 요소의 tr 가져오기
    const tr = check.closest("tr");
    console.log(tr);

    let inventoryId = tr.querySelector("[name='inventoryId']").textContent;
    let inventoryStatus = tr.querySelector("[name='inventoryStatus']").value;
    let warehouseName = tr.querySelector("[name='toWarehouse']").value;
    // let quantity = tr.querySelector("[name='quantity']").value;

    result += `<input type="hidden" name="invetoryList[${idx}].inventoryId" value="${inventoryId}" />`;
    result += `<input type="hidden" name="invetoryList[${idx}].inventoryStatus" value="${inventoryStatus}" />`;
    result += `<input type="hidden" name="invetoryList[${idx}].warehouseName" value="${warehouseName}" />`;
    // result += `<input type="hidden" name="invetoryList[${idx}].quantity" value="${quantity}" />`;

    // tr에서 변경된 요소 찾기
    // tr.querySelector("")
    //document.querySelector("#moveForm").insertAdjacentHTML("beforeend", element);
  });
  document.querySelector("#moveForm").insertAdjacentHTML("beforeend", result);
  document.querySelector("#moveForm").submit();
});
