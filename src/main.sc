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
        go!: /SuggestTour

    state: Bye
        intent!: /пока
        random:
            a: Пока! До встречи!
            a: Пока-пока! Хорошего дня!
            a: До свидания! Надеюсь, увидимся снова!

    state: CatchAll || noContext = true
        event!: noMatch
        random:
            a: Извините, я не понял ваш запрос. Можете, пожалуйста, переформулировать?
            a: Упс! Похоже, я не совсем понял, что вы хотите. Попробуйте ещё раз.
            a: Я не уверен, что вы имели в виду. Можете уточнить?
            
            
    state: SuggestTour || modal = true
        a: Куда ты хочешь пойти?
        a: {{}}
        if: $request.channelType === "telegram"
            inlineButtons:
                { text: "В Камелот", url: "https://www.youtube.com/watch?v=aJkJVj0JlVI" }
                { text: "В Рим", url: "https://www.youtube.com/watch?v=aJkJVj0JlVI" }
                { text: "В Нидерланд", url: "https://www.youtube.com/watch?v=aJkJVj0JlVI" }
        else:
            buttons:
                "В Камелот"
                "В Рим"
                "В Нидерланды"
            
        state: ChooseTour
            q: * (Камелот) *
            q: * (Рим) *
            q: * (Нидерланды) *
            go!: /HowManyTickets
            
        state: LocalCatchAll
            event: noMatch
            a: Такого тура нету
            go!: ..
            
    state: HowManyTickets
        a: Сколько билетов вам нужно?
        
    state: Match
        event!: match
        a: {{$context.intent.answer}}
        
