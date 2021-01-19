var startTime;
var endTime;
var name;

function Lesson(startTime, endTime, name) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.name = name;
}

function getStartTime() {
    return startTime;
}

function getEndTime() {
    return endTime;
}

function getName() {
    return name;
}