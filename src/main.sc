require: patterns/patterns.sc
require: slotfilling/slotFilling.sc
  module = sys.zb-common
  
init:
    bind("postProcess", function($context) {
        log("|||||||||||||||||||| MY LOG"+ toPrettyString($context))    
    })

theme: /
    
    state: Start
        q!: $regex</start>
        script:
            $temp.botName = capitalize($injector.botName)
        a: Добрый день! Меня зовут {{$temp.botName}}.
        
        
    state: Hello
        intent!: /привет
        random:
            a: Привет! Рад тебя видеть!
            a: Здравствуй! Как дела?
            a: Приветствую! Чем могу помочь?
        script:
            $response.replies = $response.replies || [];
            $response.replies.push({ 
                type: "image", 
                imageUrl: "https://i.pinimg.com/736x/50/7c/a4/507ca4da5598d5ab4febb9403d434b11.jpg", 
                text: "Nagi" 
            });
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
            
    state: SuggestTour
        script:
            $response.replies = [];

            $response.replies.push({
                type: "image",
                imageUrl: "https://i.pinimg.com/736x/50/7c/a4/507ca4da5598d5ab4febb9403d434b11.jpg",
                text: "Камелот"
            });
            $response.replies.push({
                type: "image",
                imageUrl: "https://i.pinimg.com/736x/50/7c/a4/507ca4da5598d5ab4febb9403d434b11.jpg",
                text: "Рим"
            });
            $response.replies.push({
                type: "image",
                imageUrl: "https://i.pinimg.com/736x/50/7c/a4/507ca4da5598d5ab4febb9403d434b11.jpg",
                text: "Нидерланды"
            });
            $response.replies.push({
                type: "buttons",
                buttons: [
                    "Хочу в Камелот",
                    "Хочу в Рим",
                    "Хочу в Нидерланды"
                ]
            });

    state: ChooseTour
        q: * (Камелот) *
        q: * (Рим) *
        q: * (Нидерланды) *
        a: Отличный выбор! Сейчас расскажу подробнее.

            
    state: HowManyTickets
        a: Сколько билетов вам нужно?
        
    state: Match
        event!: match
        a: {{$context.intent.answer}}
        