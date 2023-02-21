package oop_lab3;
 
import java.util.Random;

import oop_lab3.human_classes.Human;

class RandomFactoryHuman implements humanFactory {
        humanFactory[] factories;
        Random r = new Random();

        /**
         * передается перечень фабрик для генерации персонажей
         * @param factories
         */
        public RandomFactoryHuman(humanFactory[] factories) {
            this.factories = factories;
        }
    
        @Override
        public Human create() 
        {
            int nextClass = r.nextInt(factories.length);
            return factories[nextClass].create();/* контруктор по умолчанию без имени */
        }
    }