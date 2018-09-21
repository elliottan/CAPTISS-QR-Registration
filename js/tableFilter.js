// function sortTableByName() {
//   var input, filter, table, tr, td, i;
//   input = document.getElementById("nameFilterInput");
//   filter = input.value.toUpperCase();
//   table = document.getElementById("myTable");
//   tr = table.getElementsByTagName("tr");

//   for (i = 1; i < tr.length; i++) { // Check all rows
//     var foundMatch = (input === ""); // Whether there was a match
//     for (var j = 0; i < tr[i].getElementsByTagName("td").length && !foundMatch; j++) {
//       td = tr[i].getElementsByTagName("td")[j];
//       if (td != null)
//         foundMatch = td.innerHTML.toUpperCase().indexOf(filter) > -1;
//     }

//     if (foundMatch) { // There was a match for this row, so display it
//       tr[i].style.display = "";
//     } else {
//       tr[i].style.display = "none";
//     }
//   }
// }

function sortTableByName(column, elementId) {
  var input, filter, table, tr, td, i;
  input = document.getElementById(elementId);
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[column];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}