require: patterns/patterns.sc
require: slotfilling/slotFilling.sc
  module = sys.zb-common

theme: /
    
    state: Start
        q!: $regex</start>
        
    state: Hello
        intent!: /привет
        random:
            a: Привет! Рад тебя видеть!
            a: Здравствуй! Как дела?
            a: Приветствую! Чем могу помочь?
        go!: /SuggestMovie

    state: Bye
        intent!: /пока
        random:
            a: Пока! До встречи!
            a: Пока-пока! Хорошего дня!
            a: До свидания! Надеюсь, увидимся снова!

    state: CatchAll
        event!: noMatch
        random:
            a: Извините, я не понял ваш запрос. Можете, пожалуйста, переформулировать?
            a: Упс! Похоже, я не совсем понял, что вы хотите. Попробуйте ещё раз.
            a: Я не уверен, что вы имели в виду. Можете уточнить?
            
            
    state: SuggestMovie
        a: Куда ты хочешь пойти?
        buttons:
            "В Камелот"
            "В Рим"
            "В Нидерланды"

    state: Match
        event!: match
        a: {{$context.intent.answer}}
        
