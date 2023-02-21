package oop_lab2;
 
import java.util.Random;

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