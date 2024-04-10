function updateClock() {
    const now = new Date();
    const hours = now.getHours().toString().padStart(2, '0');
    const minutes = now.getMinutes().toString().padStart(2, '0');
    const seconds = now.getSeconds().toString().padStart(2, '0');
    document.getElementById('clock').innerText = `${hours}:${minutes}:${seconds}`;
}
// Обновляем часы каждую секунду
setInterval(updateClock, 1000);
// Запускаем обновление сразу, чтобы избежать задержки в отображении времени
updateClock();