# To add a new cell, type '# %%'
# To add a new markdown cell, type '# %% [markdown]'
# %%
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from datetime import timedelta
import sys
plt.rcParams.update({'font.size': 9})

# %%
print('The file is running')
patient_id = sys.argv[1]
hundred_mtx = float(sys.argv[2])
hundred_6mp = float(sys.argv[3])
print('The file is running', hundred_mtx, hundred_6mp)
data = pd.read_csv('./Patients/'+patient_id+'.csv', skiprows=4)
data['Current_date'] = pd.to_datetime(data['Current_date'], format='%d/%m/%Y')


# %%
color_options = ['darkviolet', 'tab:cyan', 'tab:green']

fig, ax1 = plt.subplots()

ax1.plot(data['Current_date'], data['ANC'], color=color_options[0], label='ANC')
ax1.set_xlabel('Visit Date')
ax1.set_ylabel('ANC', color=color_options[0])
#ax1.tick_params('y', colors=color_options[0])
ax1.axhline(y=750, color=color_options[0], linestyle='dashed')
ax1.axhline(y=1500, color=color_options[0], linestyle='dashed')
ax1.text(min(data['Current_date']), 800, 'Min Target ANC')
ax1.text(min(data['Current_date']), 1550, 'Max Target ANC')

ax2 = ax1.twinx()
ax2.step(data['Current_date'], data['6MP'], label='6MP', color=color_options[1], where='post')
ax2.step(data['Current_date'], data['MTX'], label='MTX', color=color_options[2], where='post')
ax2.axhline(y=hundred_6mp, color=color_options[1], linestyle='dashed')
ax2.axhline(y=hundred_mtx, color=color_options[2], linestyle='dashed')
ax2.text(max(data['Current_date'])-timedelta(days=28), hundred_6mp+5, '100% 6MP', color=color_options[1])
ax2.text(max(data['Current_date'])-timedelta(days=28), hundred_mtx+5, '100% MTX', color=color_options[2])
ax2.set_ylim(ymin=0)
ax2.set_ylabel('Dose')
#ax2.tick_params('y', colors=color_options[1])

fig.autofmt_xdate()
fig.legend(loc='upper center', ncol=3, shadow=True)
fig.set_size_inches((5,3.5))
fig.savefig('./srcTushar/'+patient_id+'.jpg')
