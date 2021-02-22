$(function () {
    const email = document.getElementById("inputEmail");
    const msgEmail = document.getElementById("msgEmail");
    const updateForm = document.getElementById("updateForm");
    email.onchange = validateEmail;

    document.onkeyup = e => {
        (e.key === "Escape") && $('body').append($('#updateForm').hide());
        (e.key === "Enter") && update();
    }

    $(email).on('focusout', update);

    function update() {
        updateForm.checkValidity() && email.value !== email.dataset.text && $(updateForm).submit();
    }

    function validateEmail() {
        const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        email.setCustomValidity(re.test(String(email.value).toLowerCase()) ? '' : msgEmail.value);
    }

    $("#updateForm").on("submit", () => {
        const id = email.dataset.id;
        const text = email.value;
        const tr = $('#user_' + id);
        $.post(updateForm.action, {id: id, email: text},
            function (data) {
                if (data.result) {
                    if (tr.find('#updateForm')) {
                        $('body').append($('#updateForm').hide());
                    }
                    tr.find('td')[0].innerText = text;
                }
            }, "json");

        return false;
    });

    $(".block").on("click",
        function () {
            const id = $(this).data("id");
            $.post(updateForm.action, {id: id, status: "BLOCKED"},
                function (data) {
                    if (data.result) {
                        $('#user_' + id).find('td')[1].innerText = "BLOCKED";
                        $("button.block[data-id=" + id + "]").hide();
                        $("button.unblock[data-id=" + id + "]").show();
                    }
                }, "json");
        });
    $(".unblock").on("click",
        function () {
            const id = $(this).data("id");
            $.post(updateForm.action, {id: id, status: "ACTIVE"},
                function (data) {
                    if (data.result) {
                        $('#user_' + id).find('td')[1].innerText = "ACTIVE";
                        $("button.block[data-id=" + id + "]").show();
                        $("button.unblock[data-id=" + id + "]").hide();
                    }
                }, "json");
        });

    $(".edit").on("click",
        function () {
            const id = $(this).data("id");
            const tr = $(this).closest('tr');
            const td = $(tr.find('td')[0]);
            email.dataset.id = id;
            email.dataset.text = td.text();
            email.value = td.text();
            td.append($('#updateForm').show());
        });

});
