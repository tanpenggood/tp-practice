const {ipcRenderer} = require('electron')
const Timer = require('timer.js')

function startWork() {
    let workTimer = new Timer({
        ontick: ms => updateTime(ms),
        onend: () => notification()
    })
    workTimer.start(3)
}

function updateTime(ms) {
    let timerContainer = document.getElementById('timer-container')
    const s = (ms / 1000).toFixed(0)
    const minutes = (s / 60).toFixed(0)
    const seconds = s % 60
    timerContainer.innerText = `${minutes.toString().padStart(2, 0)}: ${seconds.toString().padStart(2, 0)}`
}

async function notification() {
    let res = await ipcRenderer.invoke('work-notification')
    if (res === 'rest') {
        setTimeout(() => alert('休息'), 5 * 1000)
    } else if (res === 'work') {
        startWork()
    }
}

startWork()