package schedule.animals;

import schedule.tasks.Task;
import java.lang.Enum;

public enum Species {
    coyote {
        public Task feedingTask() {
            // starthour = 19:00
            return new Task(1, "Feeding", 0, 3);
        }
    },
    porcupine {
        public Task feedingTask() {
            // starthour = 19:00
            return new Task(1, "Feeding", 5, 3);
        }
    },
    fox {
        public Task feedingTask() {
            // starthour = 00:00
            return new Task(1, "Feeding", 5, 3);
        }
    },
    raccoon {
        public Task feedingTask() {
            // starthour = 00:00
            return new Task(1, "Feeding", 5, 3);
        }
    },
    beaver {
        public Task feedingTask() {
            // starthour = 08:00
            return new Task(1, "Feeding", 5, 3);
        }
    };

    public abstract Task feedingTask();

}
