<script>
    var taskSelectorInitialized = false
    function onTaskChanged() {
        var taskId = $("#task-selector").val();
        window.location = window.location.origin + window.location.pathname + '/' + taskId;
    }

    function onSafeTaskChanged() {
        if (taskSelectorInitialized) {
            onTaskChanged()
        }
    }
</script>
