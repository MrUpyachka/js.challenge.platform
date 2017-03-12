<script>
    function onTaskChanged() {
        var taskId = $("#task-selector").val();
        window.location = window.location.origin + window.location.pathname + '/' + taskId;
    }
</script>
