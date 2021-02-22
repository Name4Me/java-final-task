$(function() {
    const timer = document.getElementById("timer");
    const saveForm = document.getElementById("saveForm");
    const completeBtn = $('#completeBtn');
    const updateTimer = () => {
        timer.innerHTML = (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds);
    };
    let minutes = timer.innerHTML.split(":")[0]*1;
    let seconds = 0;

    updateListener();
    startTimer();

    function complete(){
        const quizId = this.dataset.id;
        $.post(saveForm.action+"/assignment", {quizId : quizId, action : "complete"},
            function (){window.location.href=saveForm.action;});
    }

    function updateListener(){
        completeBtn.off('click').on('click', complete);
        $("li.question_item").off('click').on('click', showQuestion);
        $(".answer").off('change').on('change', saveAnswer);
    }

    function showQuestion(){
        const id = this.dataset.id
        $(".question_content").html($('div#question'+id).html());
        updateListener();
    }

    function getResult(){
        const inputs = $("div.question_content").find("input");
        let result = 0;
        $.each(inputs, function(index, element){ result += (element.checked ? 1 : 0) << index });
        return result;
    }

    function saveAnswer(){
        const id = this.dataset.id;
        const result = getResult(this);
        $.post(saveForm.action+"/assignment", {id : id, result : result,  action : "saveAnswer"} , function(){ console.log("+")});
    }

    function startTimer() {
        if (seconds === 0 || --seconds === 0) {
            if (--minutes === 0) return completeBtn.click();
            seconds = 59;
        }
        updateTimer();
        setTimeout(startTimer, 1000);
    }
});