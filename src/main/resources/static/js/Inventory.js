//document.addEventListener("DOMContentLoaded", search);

// 검색 함수: 서버에 검색 요청을 보내고 결과를 테이블에 표시
document.querySelector("#searchForm").addEventListener("submit", (e) => {
  e.preventDefault();

  var name = document.getElementById("productName");
  var code = document.getElementById("productCode");

  if (name.value === "") {
    alert("상품이름이 비어 있습니다");
    name.focus();
    return;
  } else if (code.value === "") {
    alert("상품코드가 비어 있습니다");
    code.focus();
    return;
  }

  e.target.submit();
});

/////////////////////////////////////////////////

// 체크박스 전체 선택/해제 함수
function selectAllCheckboxes(source) {
  console.log("selectAllCheckboxes called with:", source.checked);
  var checkboxes = document.getElementsByClassName("delete-checkbox");
  for (var i = 0, n = checkboxes.length; i < n; i++) {
    checkboxes[i].checked = source.checked;
    console.log("Checkbox", i, "set to", source.checked);
  }
}

// 선택된 항목 삭제 함수
function deleteSelected() {
  console.log("deleteSelected function called");
  var selectedIds = [];
  var checkboxes = document.getElementsByClassName("delete-checkbox");
  for (var i = 0; i < checkboxes.length; i++) {
    if (checkboxes[i].checked) {
      selectedIds.push(checkboxes[i].getAttribute("data-id"));
      console.log("Selected ID added:", checkboxes[i].getAttribute("data-id"));
    }
  }

  if (selectedIds.length > 0) {
    console.log("Selected IDs to delete:", selectedIds);
    fetch("/api/inventories/delete", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(selectedIds),
    }).then((response) => {
      if (response.ok) {
        console.log("Delete request successful, reloading page");
        window.location.reload();
      } else {
        console.log("Delete request failed");
        alert("삭제 실패");
      }
    });
  } else {
    console.log("No items selected for deletion");
    alert("삭제할 항목을 선택하세요.");
  }
}
